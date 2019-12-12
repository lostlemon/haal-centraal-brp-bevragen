package nl.vng.realisatie.haalcentraal.core.config;

import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.model.Afstamming;
import nl.vng.realisatie.haalcentraal.core.model.Partnerschap;
import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.core.model.QAfstamming;
import nl.vng.realisatie.haalcentraal.core.model.Verblijfplaats;
import nl.vng.realisatie.haalcentraal.core.repository.AfstammingRepository;
import nl.vng.realisatie.haalcentraal.core.repository.PartnerschapRepository;
import nl.vng.realisatie.haalcentraal.core.repository.PersoonRepository;
import nl.vng.realisatie.haalcentraal.core.repository.VerblijfplaatsRepository;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderAanduidingEnum;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class RivgLoader {

    @Inject
    private PersoonRepository persoonRepository;

    @Inject
    private PartnerschapRepository partnerschapRepository;

    @Inject
    private AfstammingRepository afstammingRepository;

    @Inject
    private VerblijfplaatsRepository verblijfplaatsRepository;

    final Map<String, Consumer<HashMap<String, String>>> handlers = new LinkedHashMap<>();

    final Map<String, BiConsumer<Verblijfplaats, String>> verblijfsPlaatsSetters = new HashMap<>();

    final Map<String, BiConsumer<Persoon, String>> persoonSetters = new HashMap<>();

    public RivgLoader() {
        handlers.put("Personen", (line) -> loadPersoon(line));
        handlers.put("Partnerschappen", (line) -> loadPartner(line));
        handlers.put("Ouder1", (line) -> loadOuder1(line));
        handlers.put("Ouder2", (line) -> loadOuder2(line));
        handlers.put("Kinderen", (line) -> loadKinderen(line));
        handlers.put("Verblijfsplaatsen", (line) -> loadVerblijfsplaats(line));

        persoonSetters.put("01.01.20", (p, bsnStringValue) -> p.setBsn(Long.valueOf(bsnStringValue)));
        persoonSetters.put("01.02.10", Persoon::setVoornamen);
        persoonSetters.put("01.02.30", Persoon::setVoorvoegsel);
        persoonSetters.put("01.02.40", Persoon::setGeslachtsnaam);
        persoonSetters.put("01.03.10", Persoon::setGeboorteDatum);
        persoonSetters.put("01.03.20", Persoon::setGeboortePlaats);
        persoonSetters.put("01.03.30", Persoon::setGeboorteLand);
        persoonSetters.put("01.04.10", Persoon::setGeslachtsaanduiding);
        persoonSetters.put("01.61.10", Persoon::setAanduidingAanschrijving);
        persoonSetters.put("01.83.10", (p, v) -> {
            p.setInOnderzoekGeboorteDatum("010310".equals(v));
            p.setInOnderzoekNaam("010200".equals(v));
            p.setInOnderzoek("010000".equals(v));
        });
        persoonSetters.put("06.08.10", Persoon::setOverlijdenDatum);
        persoonSetters.put("06.08.20", Persoon::setOverlijdenPlaats);
        persoonSetters.put("06.08.30", Persoon::setOverlijdenLand);
        persoonSetters.put("07.67.10", Persoon::setDatumOpschortingBijhouding);
        persoonSetters.put("07.67.20", Persoon::setRedenOpschortingBijhouding);
        persoonSetters.put("11.32.10", Persoon::setIndicatieGezagMinderjarige);

        verblijfsPlaatsSetters.put("09.10", Verblijfplaats::setGemeenteVanInschrijving);
        verblijfsPlaatsSetters.put("09.20", Verblijfplaats::setDatumInschrijvingInGemeente);
        verblijfsPlaatsSetters.put("10.10", Verblijfplaats::setFunctieAdres);
        verblijfsPlaatsSetters.put("10.30", Verblijfplaats::setDatumAanvangAdreshouding);
        verblijfsPlaatsSetters.put("11.10", Verblijfplaats::setStraatnaam);
        verblijfsPlaatsSetters.put("11.15", Verblijfplaats::setNaamOpenbareruimte);
        verblijfsPlaatsSetters.put("11.20", (v, s) -> v.setHuisnummer(Integer.valueOf(s)));
        verblijfsPlaatsSetters.put("11.30", Verblijfplaats::setHuisletter);
        verblijfsPlaatsSetters.put("11.40", Verblijfplaats::setHuisnummerToevoeging);
        verblijfsPlaatsSetters.put("11.50", Verblijfplaats::setAanduidingBijHuisnummer);
        verblijfsPlaatsSetters.put("11.60", Verblijfplaats::setPostcode);
        verblijfsPlaatsSetters.put("11.70", Verblijfplaats::setWoonplaatsnaam);
        verblijfsPlaatsSetters.put("11.80", Verblijfplaats::setIdentificatiecodeVerblijfplaats);
        verblijfsPlaatsSetters.put("11.90", Verblijfplaats::setIdentificatiecodeNummeraanduiding);
        verblijfsPlaatsSetters.put("12.10", Verblijfplaats::setLocatiebeschrijving);
        verblijfsPlaatsSetters.put("14.10", Verblijfplaats::setLandVanWaarIngeschreven);
        verblijfsPlaatsSetters.put("14.20", Verblijfplaats::setDatumVestigingInNederland);
        verblijfsPlaatsSetters.put("85.10", Verblijfplaats::setIngangsdatumGeldigheid);
    }

    Optional<Persoon> huidigPersoon = null;

    public void loadRivg(String fileName) throws Exception {

        log.debug("Loading: " + fileName);

        final Set<String> headers = getReader(fileName).lines()
                .findFirst()
                .map(header -> loadHeader(header))
                .orElseThrow(IllegalArgumentException::new);

        handlers.forEach((naam, f) -> {
            log.debug("Start: " + naam);
            getReader(fileName).lines()
                    .skip(1) // skip header
                    .map(line -> convertToHashMap(headers, line))
                    .peek(line -> {
                        final String currentBsn = line.get("01.01.20");
                        if (StringUtils.hasText(currentBsn)) {
                            final Long bsn = Long.valueOf(currentBsn);
                            huidigPersoon = persoonRepository.findByBsn(bsn);
                        }
                    })
                    .forEachOrdered(f);
        });

        log.info("Personen geladen: " + persoonRepository.count());
        log.info("Partnerschappen geladen: " + partnerschapRepository.count());
        log.info("Afstammingen geladen: " + afstammingRepository.count());
        log.info("Verblijfplaats geladen: " + verblijfplaatsRepository.count());
    }

    private BufferedReader getReader(String fileName) {
        final InputStream inputStream = RivgLoader.class.getResourceAsStream(fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        return br;
    }

    private Set<String> loadHeader(String header) {

        final LinkedHashSet<String> result = new LinkedHashSet<>();

        Arrays.asList(header.split(","))
                .stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(StringUtils::hasText)
                .collect(Collectors.toCollection(() -> result));
        return result;
    }

    private void loadPersoon(final HashMap<String, String> line) {

        if (StringUtils.hasText(line.get("01.01.20")) && StringUtils.isEmpty(line.get("01.H"))) { // bsn

            final Long bsn = Long.valueOf(line.get("01.01.20"));

            if (!persoonRepository.existsByBsn(bsn)) {

                final Persoon persoon = new Persoon();

                persoonSetters.entrySet().forEach(e -> {

                    final String value = line.get(e.getKey());

                    if (StringUtils.hasText(value)) {
                        e.getValue().accept(persoon, value);
                    }
                });

                persoonRepository.save(persoon);

            } else {
                log.error("Persoon " + bsn + " al in de database");
            }
        }
    }

    private void loadKinderen(final HashMap<String, String> line) {

        final String kindBsn = line.get("09.01.20");
        if (StringUtils.hasText(kindBsn) && StringUtils.isEmpty(line.get("09.H"))) {
            if (huidigPersoon.isPresent()) {
                createAfstamming("" + huidigPersoon.get().getBsn(), kindBsn, null, null);
            } else {
                log.error("No current persoon but found child: " + line);
            }
        }
    }

    private void loadOuder1(final HashMap<String, String> line) {
        final String ouder1Bsn = line.get("02.01.20");
        final String datumIngang = line.get("02.62.10");
        if (StringUtils.hasText(ouder1Bsn)
                && StringUtils.isEmpty(line.get("02.H"))
                && huidigPersoon.isPresent()) {
            createAfstamming(ouder1Bsn, "" + huidigPersoon.get().getBsn(), OuderAanduidingEnum.OUDER1, datumIngang);
        }
    }

    private void loadOuder2(final HashMap<String, String> line) {
        final String ouder2Bsn = line.get("03.01.20");
        final String datumIngang = line.get("03.62.10");
        if (StringUtils.hasText(ouder2Bsn)
                && StringUtils.isEmpty(line.get("03.H"))
                && huidigPersoon.isPresent()) {
            createAfstamming(ouder2Bsn, "" + huidigPersoon.get().getBsn(), OuderAanduidingEnum.OUDER2, datumIngang);
        }
    }

    private void loadVerblijfsplaats(final HashMap<String, String> line) {

        final String gemeenteVanInschrijving = line.get("08.09.10"); //gemeente van inschrijving
        final String datumAanvangAdresHouding = line.get("08.10.30"); //gemeente van inschrijving
        if (StringUtils.hasText(line.get("01.01.20")) && StringUtils.isEmpty(line.get("08.H"))) {
            final Boolean alreadyExists = persoonRepository.existsByBsn(Long.valueOf(line.get("01.01.20")));
            if (StringUtils.hasText(gemeenteVanInschrijving) && StringUtils.hasText(datumAanvangAdresHouding) && huidigPersoon.isPresent() && !alreadyExists) {

                final Verblijfplaats verblijfplaats = new Verblijfplaats();

                verblijfplaats.setPersoon(huidigPersoon.get());

                verblijfsPlaatsSetters.forEach((key, value) -> {

                    final String csvValue = line.get("08." + key);

                    if (StringUtils.hasText(csvValue)) {
                        value.accept(verblijfplaats, csvValue);
                    }
                });
                verblijfplaatsRepository.save(verblijfplaats);

                if (huidigPersoon.get().getVerblijfplaatsHuidig() == null) {
                    huidigPersoon.get().setVerblijfplaatsHuidig(verblijfplaats);
                } else {

                    if (StringUtils.hasText(huidigPersoon.get().getVerblijfplaatsHuidig().getGemeenteVanInschrijving())
                            && StringUtils.hasText(verblijfplaats.getGemeenteVanInschrijving())
                    ) {

                        final Long persoonVestigingDatumAanvangAdreshouding = Long.valueOf(huidigPersoon.get().getVerblijfplaatsHuidig().getDatumAanvangAdreshouding());
                        final Long huidigeVestigingDatumAanvangAdreshouding = Long.valueOf(verblijfplaats.getDatumAanvangAdreshouding());

                        if (huidigeVestigingDatumAanvangAdreshouding > persoonVestigingDatumAanvangAdreshouding) {
                            huidigPersoon.get().setVerblijfplaatsHuidig(verblijfplaats);
                        }

                    } else {
                        log.error("verblijfplaats has not datum aanvang: " + verblijfplaats.getId());
                    }
                }
            }
        }
    }

    private void createAfstamming(final String ouderBsn, final String kindBsn, final OuderAanduidingEnum ouderAanduidingEnum, final String datumIngang) {

        final Optional<Persoon> ouder = persoonRepository.findByBsn(Long.valueOf(ouderBsn));
        final Optional<Persoon> kind = persoonRepository.findByBsn(Long.valueOf(kindBsn));

        if (kind.isPresent() && ouder.isPresent()
                && !afstammingRepository.exists(QAfstamming.afstamming.ouder.eq(ouder.get()).and(QAfstamming.afstamming.kind.eq(kind.get())))) {
            final Afstamming afstamming = new Afstamming();

            afstamming.setOuder(ouder.get());
            afstamming.setKind(kind.get());
            afstamming.setOuderAanduidingEnum(ouderAanduidingEnum);
            afstamming.setDatumIngangFamilierechtelijkeBetrekking(datumIngang);
            afstammingRepository.save(afstamming);
        }
    }

    private void loadPartner(final HashMap<String, String> line) {

        final String partnerBsn = line.get("05.01.20");

        if (StringUtils.hasText(partnerBsn) && huidigPersoon.isPresent()) {

            final Optional<Persoon> partners2 = persoonRepository.findByBsn(Long.valueOf(partnerBsn));

            if (partners2.isPresent()) {

                final Partnerschap partnerschap = new Partnerschap();

                partnerschap.setPartner1(huidigPersoon.get());
                partnerschap.setPartner2(partners2.get());
                partnerschap.setDatumOntbinding(StringUtils.hasText(line.get("05.07.10")) ? line.get("05.07.10") : null);
                partnerschap.setSoortVerbintenis(line.get("05.15.10"));
                partnerschap.setAangaanHuwelijkPartnerschapDatum(StringUtils.hasText(line.get("05.06.10")) ? line.get("05.06.10") : null);
                partnerschap.setAangaanHuwelijkPartnerschapPlaats(line.get("05.06.20"));
                partnerschap.setAangaanHuwelijkPartnerschapLand(line.get("05.06.30"));
                partnerschap.setIndicatieOnjuist(line.get("05.84.10"));

                partnerschapRepository.save(partnerschap);

            } else {
                log.warn("Partner met bsn " + partnerBsn + ", niet gevonden");
            }
        }
    }

    private HashMap<String, String> convertToHashMap(Set<String> headers, String line) {

        final HashMap<String, String> result = new HashMap<>();

        final Iterator<String> headerIterator = headers.iterator();

        Arrays.asList(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))
                .stream()
                .map(String::trim)
                .forEachOrdered(value -> result.put(headerIterator.next(), value));

        return result;
    }

}

package nl.vng.realisatie.haalcentraal.web.converter;

import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.enums.IndicatieGezagEnum;
import nl.vng.realisatie.haalcentraal.core.enums.OpschortingEnum;
import nl.vng.realisatie.haalcentraal.core.model.Afstamming;
import nl.vng.realisatie.haalcentraal.core.model.Partnerschap;
import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.core.model.QAfstamming;
import nl.vng.realisatie.haalcentraal.core.model.QPartnerschap;
import nl.vng.realisatie.haalcentraal.core.repository.AfstammingRepository;
import nl.vng.realisatie.haalcentraal.core.repository.PartnerschapRepository;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.DatumOnvolledig;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.GeslachtEnum;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Gezagsverhouding;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.HalLink;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonEmbedded;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonLinks;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Kiesrecht;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OpschortingBijhouding;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Overlijden;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.SoortAdresEnum;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Verblijfplaats;
import nl.vng.realisatie.haalcentraal.web.Util;
import nl.vng.realisatie.haalcentraal.web.controller.BevraagPersoonController;
import nl.vng.realisatie.haalcentraal.web.exception.ExpandException;
import nl.vng.realisatie.haalcentraal.web.exception.FieldsException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
public class PersoonConverterImpl extends BaseConverterImpl<IngeschrevenPersoonHal, Persoon> implements PersoonConverter {

    @Inject
    private AfstammingRepository afstammingRepository;

    @Inject
    private PartnerschapRepository partnerschapRepository;

    @Inject
    private NaamPersoonConverter naamPersoonConverter;

    @Inject
    private KindConverter kindConverter;

    @Inject
    private OuderConverter ouderConverter;

    @Inject
    private PartnerConverter partnerConverter;

    @Inject
    private GeboorteConverter geboorteConverter;

    @Inject
    private LeeftijdService leeftijdService;


    public static Set<String> leaf(final Set<String> fields, String field) {

        if (fields == null) {
            return null;
        }

        return fields.stream() //
                .filter(s -> s.startsWith(field + ".")) //
                .map(s -> s.substring(field.length() + 1)) //
                .filter(StringUtils::hasText) //
                .collect(Collectors.toSet());
    }

    private static final Handler<IngeschrevenPersoonHal, Persoon> noop = (ingeschrevenPersoon, persoon, fields, expand) -> {
    };

    private final Map<String, Handler<IngeschrevenPersoonHal, Persoon>> expandMap = new HashMap<>();

    public PersoonConverterImpl() {

        getMap().put("naam", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.naam(naamPersoonConverter.convert(persoon, fields, expand)));

        getMap().put("burgerservicenummer", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.burgerservicenummer(calcBsn(persoon)));

        getMap().put("kiesrecht", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.kiesrecht(calcKiesrecht()));

        getMap().put("geboorte", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.setGeboorte(geboorteConverter.convert(persoon, fields, expand)));

        getMap().put("leeftijd", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.leeftijd(leeftijdService.calcLeeftijd(persoon).orElse(null)));

        getMap().put("geslachtsaanduiding", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.geslachtsaanduiding(calcGeslachstaanduiding(persoon)));

        getMap().put("overlijden", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.overlijden(calcOverlijden(persoon)));

        getMap().put("verblijfsplaats", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.verblijfplaats(calcVerblijfplaats(persoon)));

        getMap().put("datumEersteInschrijvingGBA", (ingeschrevenPersoon, persoon, fields, expand) ->
                ingeschrevenPersoon.datumEersteInschrijvingGBA(calcDatumEersteInschrijvingGBA(persoon)));

        // burgerlijkeStaat zit niet in het swagger contract van BIP
        getMap().put("burgerlijkeStaat", noop);

        getMap().put("ouders", (ingeschrevenPersoonHal, persoon, fields, expand) ->
                calcFieldsOuders(ingeschrevenPersoonHal, persoon, fields));

        getMap().put("kinderen", (ingeschrevenPersoonHal, persoon, fields, expand) ->
                calcFieldsKinderen(ingeschrevenPersoonHal, persoon, fields));

        getMap().put("partners", (ingeschrevenPersoonHal, persoon, fields, expand) ->
                calcFieldsPartners(ingeschrevenPersoonHal, persoon, fields));

        expandMap.put("kinderen", (ingeschrevenPersoonHal, persoon, fields, expand) -> calcExpandKinderen(ingeschrevenPersoonHal, persoon, expand));
        expandMap.put("partners", (ingeschrevenPersoonHal, persoon, fields, expand) -> calcExpandPartners(ingeschrevenPersoonHal, persoon, expand));
        expandMap.put("ouders", (ingeschrevenPersoonHal, persoon, fields, expand) -> calcExpandOuders(ingeschrevenPersoonHal, persoon, expand));
    }

    @Override
    public IngeschrevenPersoonHal createTarget(final Persoon persoon, final Set<String> fields) {
        final IngeschrevenPersoonHal ingeschrevenPersoonHal = new IngeschrevenPersoonHal();
        ingeschrevenPersoonHal.setLinks(new IngeschrevenPersoonLinks());
        return ingeschrevenPersoonHal;
    }

    @Override
    public IngeschrevenPersoonHal convert(final Persoon persoon, final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Set<String> fields, final Set<String> expand) {

        // show all fields when no fields are requested
        super.convert(persoon, ingeschrevenPersoonHal, fields == null || fields.isEmpty() ? getMap().keySet() : fields, null);

        log.debug("fields=" + fields + ", exapnd=" + expand);
        ingeschrevenPersoonHal.getLinks().self(createIngeschrevenPersoonLink(persoon.getBsn(), fields, expand));

        ingeschrevenPersoonHal.geheimhoudingPersoonsgegevens(calcGeheimhouding(persoon).orElse(null));

        ingeschrevenPersoonHal.opschortingBijhouding(StringUtils.hasText(persoon.getRedenOpschortingBijhouding())
                ? calcOpschortingBijhouding(persoon)
                : null);

        ingeschrevenPersoonHal.gezagsverhouding(StringUtils.hasText(persoon.getIndicatieGezagMinderjarige())
                ? calcGezagsverhouding(persoon)
                : null);

        return ingeschrevenPersoonHal;
    }

    @Override
    public void handleExpand(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> expand) {
        log.debug("expat2=" + expand + ", keyset=" + expandMap.keySet());
        if (expand != null) {
            expand.stream() //
                    .filter(expandField -> !expandMap.keySet().contains(expandField) && expandMap.keySet().stream().noneMatch(i -> expandField.startsWith(i + "."))) //
                    .findAny() //
                    .ifPresent(entry -> {
                        throw new ExpandException(entry);
                    });
            expand.stream() //
                    .map(s -> {
                        if (s.contains(".")) {
                            return s.substring(0, s.indexOf("."));
                        } else {
                            return s;
                        }
                    })
                    .distinct()
                    .forEach(e -> {
                        expandMap.get(e).handle(ingeschrevenPersoonHal, persoon, null, leaf(expand, e));
                    });
        }
    }

    private OpschortingBijhouding calcOpschortingBijhouding(final Persoon persoon) {

        return new OpschortingBijhouding()
                .reden(OpschortingEnum.fromCode(persoon.getRedenOpschortingBijhouding()))
                .datum(convertDatum(persoon.getDatumOpschortingBijhouding()).orElse(null));
    }

    private Gezagsverhouding calcGezagsverhouding(final Persoon persoon) {
        return new Gezagsverhouding().indicatieGezagMinderjarige(IndicatieGezagEnum.getIndicatieGezagMinderjarige(persoon.getIndicatieGezagMinderjarige()));
    }

    private void calcFieldsOuders(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> fields) {

        // no leftovers
        if (fields != null && !fields.isEmpty()) {
            throw new FieldsException(fields.stream().collect(Collectors.joining(",")));
        }

//        ingeschrevenPersoonHal.getLinks().addOudersItem(createOudersLink.apply(persoon.getBsn()));

        afstammingRepository.findAll(QAfstamming.afstamming.kind.eq(persoon), PageRequest.of(0, Integer.MAX_VALUE)) //
                .stream() //
                .map(createOuderLink) //
                .forEachOrdered((oudersItem) -> ingeschrevenPersoonHal.getLinks().addOudersItem(oudersItem));
    }

    private void calcFieldsKinderen(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> fields) {

        // no leftovers
        if (fields != null && !fields.isEmpty()) {
            throw new FieldsException(fields.stream().collect(Collectors.joining(",")));
        }

//        ingeschrevenPersoonHal.getLinks().addKinderenItem(createKinderensLink.apply(persoon.getBsn()));

        afstammingRepository.findAll(QAfstamming.afstamming.ouder.eq(persoon), PageRequest.of(0, Integer.MAX_VALUE)) //
                .stream() //
                .map(createKindLink) //
                .forEachOrdered((kindItem) -> ingeschrevenPersoonHal.getLinks().addKinderenItem(kindItem));

    }

    private void calcFieldsPartners(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> fields) {

        // no leftovers
        if (fields != null && !fields.isEmpty()) {
            throw new FieldsException(fields.stream().collect(Collectors.joining(",")));
        }

//        ingeschrevenPersoonHal.getLinks().addPartnersItem(createPartnerssLink.apply(persoon.getBsn()));

        partnerschapRepository.findAll(QPartnerschap.partnerschap.partner1.eq(persoon), PageRequest.of(0, Integer.MAX_VALUE)) //
                .stream() //
                .map(p -> createPartnerLink.apply(p)) //
                .forEachOrdered((partnerItem) -> ingeschrevenPersoonHal.getLinks().addPartnersItem(partnerItem));
    }

    private void calcExpandOuders(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> expand) {

        if (ingeschrevenPersoonHal.getEmbedded() == null) {
            ingeschrevenPersoonHal.setEmbedded(new IngeschrevenPersoonEmbedded());
        }

        ouderConverter.validate(expand);

        afstammingRepository.findAll(QAfstamming.afstamming.kind.eq(persoon), PageRequest.of(0, Integer.MAX_VALUE)) //
                .stream() //
                .map(a -> ouderConverter.convert(a, expand, null)) // expand becomes fields
                .forEach(o -> ingeschrevenPersoonHal.getEmbedded().addOudersItem(o));

    }

    private void calcExpandKinderen(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> expand) {

        if (ingeschrevenPersoonHal.getEmbedded() == null) {
            ingeschrevenPersoonHal.setEmbedded(new IngeschrevenPersoonEmbedded());
        }

        kindConverter.validate(expand);

        afstammingRepository.findAll(QAfstamming.afstamming.ouder.eq(persoon), PageRequest.of(0, Integer.MAX_VALUE)) //
                .stream() //
                .map(p -> kindConverter.convert(p, expand, null)) // expand becomes fields
                .forEach(k -> ingeschrevenPersoonHal.getEmbedded().addKinderenItem(k));

    }

    private void calcExpandPartners(final IngeschrevenPersoonHal ingeschrevenPersoonHal, final Persoon persoon, final Set<String> expand) {

        if (ingeschrevenPersoonHal.getEmbedded() == null) {
            ingeschrevenPersoonHal.setEmbedded(new IngeschrevenPersoonEmbedded());
        }

        partnerConverter.validate(expand);

        partnerschapRepository.findAll(QPartnerschap.partnerschap.partner1.eq(persoon), PageRequest.of(0, Integer.MAX_VALUE)) //
                .stream() //
                .map(p -> partnerConverter.convert(p, expand, null)) // expand becomes fields
                .forEach(p -> ingeschrevenPersoonHal.getEmbedded().addPartnersItem(p));

    }

    public static final HalLink createIngeschrevenPersoonLink(final Long bsn, final Set<String> fields, final Set<String> expand) {
        return createIngeschrevenPersoonLink(Util.formatBsn.apply(bsn), fields, expand);
    }

    public static final HalLink createIngeschrevenPersoonLink(final String bsn, final Set<String> fields, final Set<String> expand) {
        return new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, bsn) //
                .ingeschrevenNatuurlijkPersoon(bsn,
                        null,
                        expand != null ? expand.stream().collect(Collectors.joining(",")) : null,
                        fields != null ? fields.stream().collect(Collectors.joining(",")) : null
                )
            ).toUri());
    }

    public static final Function<Long, HalLink> createOudersLink = bsn -> //
            new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, bsn) //
                    .ingeschrevenpersonenBurgerservicenummerouders(Util.formatBsn.apply(bsn), null)
            ).toUri());

    public static final Function<Long, HalLink> createKinderensLink = bsn -> //
            new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, bsn) //
                    .ingeschrevenpersonenBurgerservicenummerkinderen(Util.formatBsn.apply(bsn), null)
            ).toUri());

    public static final Function<Long, HalLink> createPartnerssLink = bsn -> //
            new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, bsn) //
                    .ingeschrevenpersonenBurgerservicenummerpartners(Util.formatBsn.apply(bsn), null)
            ).toUri());

    public static final Function<Partnerschap, HalLink> createPartnerLink = (p) -> //
            new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, p.getPartner1().getBsn(), "" + p.getPartner2().getBsn()) //
                    .ingeschrevenpersonenBurgerservicenummerpartnersUuid(Util.formatBsn.apply(p.getPartner1().getBsn()), "" + p.getPartner2().getBsn(), null)
            ).toUri());

    public static final Function<Afstamming, HalLink> createOuderLink = (a) -> //
            new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, a.getKind().getBsn(), a.getOuder().getBsn())
                    .ingeschrevenpersonenBurgerservicenummeroudersUuid(Util.formatBsn.apply(a.getKind().getBsn()), Util.formatBsn.apply(a.getOuder().getBsn()), null)
            ).toUri());

    public static final Function<Afstamming, HalLink> createKindLink = (a) ->
            new HalLink().href(linkTo(methodOn(BevraagPersoonController.class, a.getOuder().getBsn(), a.getKind().getBsn())
                    .ingeschrevenpersonenBurgerservicenummerkinderenUuid(Util.formatBsn.apply(a.getOuder().getBsn()), Util.formatBsn.apply(a.getKind().getBsn()), null)
            ).toUri());

    public static String calcBsn(Persoon persoon) {
        return Util.formatBsn.apply(persoon.getBsn());
    }

    public static Overlijden calcOverlijden(Persoon persoon) {

        Overlijden overlijden = null;

        if (persoon.getOverlijdenDatum() != null) {
            overlijden = new Overlijden();
            overlijden.setDatum(convertDatum(persoon.getOverlijdenDatum()).orElse(null));
        }
        if (persoon.getRedenOpschortingBijhouding() != null && persoon.getRedenOpschortingBijhouding().equals("O") && persoon.getDatumOpschortingBijhouding() != null) {
            overlijden = new Overlijden();
            overlijden.datum(convertDatum(persoon.getDatumOpschortingBijhouding()).orElse(null));
        }
        if (persoon.getOverlijdenLand() != null) {
            overlijden.land(calcLand(persoon.getOverlijdenLand()));
        }
        if (persoon.getOverlijdenPlaats() != null) {
            overlijden.plaats(calcPlaats(persoon.getOverlijdenPlaats()));
        }
        return overlijden;
    }

    public static Verblijfplaats calcVerblijfplaats(final Persoon persoon) {
        final nl.vng.realisatie.haalcentraal.core.model.Verblijfplaats huidig = persoon.getVerblijfplaatsHuidig();

        Verblijfplaats verblijfplaats = new Verblijfplaats();

        if (huidig != null) {

            verblijfplaats.datumVestigingInNederland(convertDatum(huidig.getDatumVestigingInNederland()).orElse(null));
            verblijfplaats.gemeenteVanInschrijving(calcPlaats(huidig.getGemeenteVanInschrijving()));
            verblijfplaats.landVanwaarIngeschreven(calcLand(huidig.getLandVanWaarIngeschreven()));

            verblijfplaats.naamOpenbareRuimte(huidig.getNaamOpenbareruimte())
                .huisnummer(huidig.getHuisnummer())
                .huisletter(huidig.getHuisletter())
                .identificatiecodeNummeraanduiding(huidig.getIdentificatiecodeNummeraanduiding())
                .huisnummertoevoeging(huidig.getHuisnummerToevoeging())
                .functieAdres(calcFunctieAdres(huidig.getFunctieAdres()))
                .postcode(huidig.getPostcode());

        }
        return verblijfplaats;
    }

    public static SoortAdresEnum calcFunctieAdres(final String value) {

        SoortAdresEnum result = null;
        switch (value.toLowerCase()) {
            case "w":
                result = SoortAdresEnum.WOONADRES;
                break;
            case "b":
                result = SoortAdresEnum.BRIEFADRES;
                break;
            default:
                result = null;
        }
        return result;
    }

    public static Kiesrecht calcKiesrecht() {
        final Kiesrecht result = new Kiesrecht();
        return result;
    }

    private static Optional<Boolean> calcGeheimhouding(Persoon persoon) {
        if (persoon.getIndicatieGeheim() != null && persoon.getIndicatieGeheim()) {
            return Optional.of(true);
        } else {
            return Optional.empty();
        }
    }

    private static DatumOnvolledig calcDatumEersteInschrijvingGBA(Persoon persoon) {
        return convertDatum(persoon.getDatumEersteInschrijvingGBA()).orElse(null);
    }

    public static GeslachtEnum calcGeslachstaanduiding(final Persoon persoon) {

        final GeslachtEnum result;

        if (persoon.getGeslachtsaanduiding() != null) {

            switch (persoon.getGeslachtsaanduiding()) {
                case "M":
                    result = GeslachtEnum.MAN;
                    break;
                case "V":
                    result = GeslachtEnum.VROUW;
                    break;
                case "O":
                    result = GeslachtEnum.ONBEKEND;
                    break;
                default:
                    result = null;
                    break;
            }

        } else {
            result = null;
        }

        return result;
    }

    public static Optional<DatumOnvolledig> convertDatum(final String dateString) {
        if (StringUtils.hasText(dateString) && dateString.length() == 8) {
            final Integer jaar = Integer.valueOf(dateString.substring(0, 4));
            final Integer maand = Integer.valueOf(dateString.substring(4, 6));
            final Integer dag = Integer.valueOf(dateString.substring(6, 8));
            return Optional.of(new DatumOnvolledig().datum(convertLocalDatum(dateString).orElse(null)).jaar(jaar != 0 ? jaar : null).maand(maand != 0 ? maand : null).dag(dag != 0 ? dag : null));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<LocalDate> calcGeboorteDatum(Persoon persoon) {
        return convertLocalDatum(persoon.getGeboorteDatum());
    }

}

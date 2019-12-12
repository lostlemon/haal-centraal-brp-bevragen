package nl.vng.realisatie.haalcentraal.web.converter;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Getter;
import nl.vng.realisatie.haalcentraal.core.enums.AanduidingAanschrijving;
import nl.vng.realisatie.haalcentraal.core.enums.AanschrijfwijzeEnum;
import nl.vng.realisatie.haalcentraal.core.enums.AdellijkeTitelPredikaat;
import nl.vng.realisatie.haalcentraal.core.model.Partnerschap;
import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.core.model.QPartnerschap;
import nl.vng.realisatie.haalcentraal.core.repository.PartnerschapRepository;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.NaamPersoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * NaamPersoonConverterImpl
 */
@Service
public class NaamPersoonConverterImpl extends BaseConverterImpl<NaamPersoon, Persoon> implements NaamPersoonConverter {

    @Autowired
    private PartnerschapRepository partnerschapRepository;

    @Override
    public NaamPersoon createTarget(final Persoon source, final Set<String> fields) {
        return new NaamPersoon();
    }

    @FunctionalInterface
    interface Mapper<S, T> {
        void apply(S source, T target);
    }

    private final Map<String, Mapper<AanschrijfWijze, StringBuilder>> aanschrijfMapping = new HashMap<>();
    private static final String STREEPJE = "-";

    @SuppressWarnings("CPD-START")
    public NaamPersoonConverterImpl() {
        getMap().put("voorletter", (naam, persoon, fields, expand) -> naam.setVoorletters(calcVoorletters(persoon.getVoornamen())));
        getMap().put("voornamen", (naam, persoon, fields, expand) -> naam.setVoornamen(persoon.getVoornamen()));
        getMap().put("geslachtsnaam", (naam, persoon, fields, expand) -> naam.setGeslachtsnaam(persoon.getGeslachtsnaam()));
        getMap().put("voorvoegsel", (naam, persoon, fields, expand) -> naam.setVoorvoegsel(persoon.getVoorvoegsel()));
        getMap().put("aanhef", (naam, persoon, fields, expand) -> naam.setAanhef(calcAanhef(persoon)));
        getMap().put("aanschrijfwijze", (naam, persoon, fields, expand) -> naam.setAanschrijfwijze(calcAanschrijfwijze(persoon)));
    }

    private static String calcAanhef(Persoon persoon) {

        if (StringUtils.hasText(persoon.getAdellijketitelPredikaat())) {
            return AdellijkeTitelPredikaat.fromCode(persoon.getAdellijketitelPredikaat()).getAanhef();
        }
        return null;
    }

    private String calcAanschrijfwijze(final Persoon persoon) {
        aanschrijfMapping.put("vl", (aanschrijf, stringBuilder) -> stringBuilder.append(aanschrijf.getVl()));
        aanschrijfMapping.put("vv", (aanschrijf, stringBuilder) -> stringBuilder.append(aanschrijf.getVv()));
        aanschrijfMapping.put("gn", (aanschrijf, stringBuilder) -> stringBuilder.append(aanschrijf.getGn()));
        aanschrijfMapping.put("at", (aanschrijf, stringBuilder) -> stringBuilder.append(calcAdellijktitel(aanschrijf.getAt())));
        aanschrijfMapping.put("ap", (aanschrijf, stringBuilder) -> stringBuilder.append(calcAdellijktitel(aanschrijf.getAp())));
        aanschrijfMapping.put("vp", (aanschrijf, stringBuilder) -> stringBuilder.append(aanschrijf.getVp()));
        aanschrijfMapping.put("gp", (aanschrijf, stringBuilder) -> stringBuilder.append(aanschrijf.getGp()));
        aanschrijfMapping.put("-", (aanschrijf, stringBuilder) -> stringBuilder.append(STREEPJE));
        StringBuilder stringBuilder = new StringBuilder();
        final List<Partnerschap> partnerschaps = new ArrayList<>();
        Persoon partner;
        Optional<Persoon> opt;
        final QPartnerschap qPartnerschap = QPartnerschap.partnerschap;
        final BooleanExpression notNullAangaanDatum = qPartnerschap.aangaanHuwelijkPartnerschapDatum.isNotNull();
        final BooleanExpression ontbinding = qPartnerschap.datumOntbinding.isNotNull().and(notNullAangaanDatum);
        final BooleanExpression nietOntbinding = qPartnerschap.datumOntbinding.isNull().and(notNullAangaanDatum);
        partnerschapRepository.findAll(QPartnerschap.partnerschap.partner1.bsn.eq(persoon.getBsn())
                .and(nietOntbinding)).forEach(partnerschaps::add);
        // meerdere partners en oudste partnerschap
        if (partnerschaps.size() > 1) {
            opt = getOudstePartner(partnerschaps, persoon);
            partner = opt.orElse(null);
            //meerdere ontbinding
        } else if (partnerschaps.size() == 1) {
            partner = partnerschaps.get(0).getPartner2();
        } else {
            partnerschapRepository.findAll(QPartnerschap.partnerschap.partner1.bsn.eq(persoon.getBsn()).and(ontbinding).and(notNullAangaanDatum));
            if (partnerschaps.size() > 1) {
                opt = getOudstePartner(partnerschaps, persoon);
                partner = opt.orElse(null);
            } else if (partnerschaps.size() == 1) {
                partner = partnerschaps.get(0).getPartner2();
            } else {
                Partnerschap partnerschap = partnerschapRepository.findOne(qPartnerschap.partner1.bsn.eq(persoon.getBsn())).orElse(new Partnerschap());
                partner = !partnerschap.isNew() ? partnerschap.getPartner2() : new Persoon();
            }
        }
        AanschrijfWijze aanschrijfWijze = new AanschrijfWijze(persoon, !partner.isNew() ? partner : new Persoon());
        List<AanschrijfwijzeEnum> samenstelling = AanschrijfwijzeEnum.calcSamenstelling(persoon);
        if (!partner.isNew()
                && aanschrijfWijze.getAt() != null
                && samenstelling.contains(AanschrijfwijzeEnum.AT)
                && aanschrijfWijze.getAt().getSoort().equals("predikaat")) {
            samenstelling.remove(AanschrijfwijzeEnum.AT);
        }
        if (!partner.isNew()
                && aanschrijfWijze.getAp() != null
                && samenstelling.contains(AanschrijfwijzeEnum.AP)
                && aanschrijfWijze.getAp().getSoort().equals("predikaat")) {
            samenstelling.remove(AanschrijfwijzeEnum.AP);
        }
        if (partner.isNew() && samenstelling.contains(AanschrijfwijzeEnum.STREEPJE)) {
            samenstelling.remove(AanschrijfwijzeEnum.STREEPJE);
        }
        samenstelling
                .stream()
                .filter(s -> aanschrijfMapping.containsKey(s.getCode()))
                .map(AanschrijfwijzeEnum::getCode)
                .forEach(s -> aanschrijfMapping.get(s).apply(aanschrijfWijze, stringBuilder));

        return stringBuilder.toString().trim();
    }

    private Optional<Persoon> getOudstePartner(final List<Partnerschap> partnerschaps, final Persoon persoon) {
        Optional<Persoon> partner = Optional.empty();
        if (!partnerschaps.isEmpty()) {
            Optional<Partnerschap> oudstePartnerschap = partnerschaps.stream()
                    .filter(p -> Integer.parseInt(p.getAangaanHuwelijkPartnerschapDatum()) != 0
                            && AanduidingAanschrijving.fromCode(persoon.getAanduidingAanschrijving()) != AanduidingAanschrijving.E)
                    .max(Comparator.comparing(Partnerschap::getAangaanHuwelijkPartnerschapDatum));
            if (oudstePartnerschap.isPresent()) {
                partner = Optional.of(oudstePartnerschap.get().getPartner2());
            }
        }
        return partner;
    }

    private String calcAdellijktitel(final AdellijkeTitelPredikaat adellijkeTitelPredikaat) {
        return adellijkeTitelPredikaat != null ? adellijkeTitelPredikaat.getOmschrijving().toLowerCase() + " " : "";
    }

    @Getter
    private static class AanschrijfWijze {
        private Persoon persoon;
        private Persoon partner;
        private String vl;//persoon_voorletters
        private String vv;//persoon_voorvoegsel
        private String gn;//persoon_geslachtsnaam
        private String vp;//partner_voorvoegsel
        private String gp;//partner_geslachtsnaam
        private AdellijkeTitelPredikaat at;//persoon_adellijk
        private AdellijkeTitelPredikaat ap;//partner_adellijk

        public AanschrijfWijze(final Persoon persoon, final Persoon partner) {
            this.persoon = persoon;
            this.partner = partner;
            vl = getText(calcVoorletters(persoon.getVoornamen()));
            vv = getText(persoon.getVoorvoegsel());
            gn = getTextWithoutSpace(persoon.getGeslachtsnaam());
            vp = getText(partner.getVoorvoegsel());
            gp = getTextWithoutSpace(partner.getGeslachtsnaam());
            at = getAdellijk(persoon.getAdellijketitelPredikaat());
            ap = getAdellijk(partner.getAdellijketitelPredikaat());
        }


        private String getText(final String str) {
            return StringUtils.hasText(str) ? str.concat(" ") : "";
        }

        private String getTextWithoutSpace(final String str) {
            return StringUtils.hasText(str) ? str : "";
        }

        private AdellijkeTitelPredikaat getAdellijk(final String code) {
            AdellijkeTitelPredikaat result = null;
            if (StringUtils.hasText(code)) {
                result = AdellijkeTitelPredikaat.fromCode(code);
            }
            return result;
        }
    }

}

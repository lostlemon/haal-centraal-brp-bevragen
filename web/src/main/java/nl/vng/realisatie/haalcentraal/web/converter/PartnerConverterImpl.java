package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.enums.VerbintenisEnum;
import nl.vng.realisatie.haalcentraal.core.model.Partnerschap;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.AangaanHuwelijkPartnerschap;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.PartnerHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.PartnerLinks;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.SoortVerbintenisEnum;
import nl.vng.realisatie.haalcentraal.web.Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.util.Set;

/**
 * PartnerConverterImpl
 * <p>
 * toont altijd partner2
 */
@Service
public class PartnerConverterImpl extends BaseConverterImpl<PartnerHal, Partnerschap> implements PartnerConverter {

    @Inject
    private NaamConverter naamConverter;

    @Inject
    private GeboorteConverter geboorteConverter;

    public PartnerConverterImpl() {
        getMap().put("burgerservicenummer", (partnerHal, partnerschap, fields, expand) -> partnerHal.setBurgerservicenummer(Util.formatBsn.apply(partnerschap.getPartner2().getBsn())));
        getMap().put("naam", (partnerHal, partnerschap, fields, expand) -> partnerHal.setNaam(naamConverter.convert(partnerschap.getPartner2(), fields, expand)));
        getMap().put("geboorte", (partnerHal, partnerschap, fields, expand) -> partnerHal.setGeboorte(geboorteConverter.convert(partnerschap.getPartner2(), fields, expand)));
        getMap().put("geslachtsaanduiding", (partnerHal, partnerschap, fields, expand) -> partnerHal.setGeslachtsaanduiding(PersoonConverterImpl.calcGeslachstaanduiding(partnerschap.getPartner1())));
        getMap().put("soortVerbintenis", (partnerHal, partnerschap, fields, expand) -> partnerHal.setSoortVerbintenis(calcSoortVerbintenis(partnerschap)));
        getMap().put("aangaanHuwelijk_Partnerschap", (partnerHal, partnerschap, fields, expand) -> partnerHal.setAangaanHuwelijkPartnerschap(calcAangaanHuwerlijk(partnerschap)));
        getMap().put("ingeschrevenpersonen", (partnerHal, partnerschap, fields, expand) -> partnerHal.getLinks().setIngeschrevenPersoon(PersoonConverterImpl.createIngeschrevenPersoonLink(partnerschap.getPartner2().getBsn(), expand, null)));
    }

    @Override
    public PartnerHal createTarget(Partnerschap source, final Set<String> fields) {
        final PartnerHal partnerHal = new PartnerHal();
        partnerHal.setLinks(calcLinks(source));
        return partnerHal;
    }

    private SoortVerbintenisEnum calcSoortVerbintenis(final Partnerschap partnerschap) {
        return VerbintenisEnum.fromCode(partnerschap.getSoortVerbintenis());
    }

    private PartnerLinks calcLinks(final Partnerschap partnerschap) {
        final PartnerLinks links = new PartnerLinks();
        links.setSelf(PersoonConverterImpl.createPartnerLink.apply(partnerschap));
        return links;
    }

    private AangaanHuwelijkPartnerschap calcAangaanHuwerlijk(final Partnerschap partnerschap) {
        AangaanHuwelijkPartnerschap result = new AangaanHuwelijkPartnerschap();
        result.datum(PersoonConverterImpl.convertDatum(partnerschap.getAangaanHuwelijkPartnerschapDatum()).orElse(null));
        result.plaats(StringUtils.hasText(partnerschap.getAangaanHuwelijkPartnerschapPlaats()) ? PersoonConverterImpl.calcPlaats(partnerschap.getAangaanHuwelijkPartnerschapPlaats()) : null);
        result.land(StringUtils.hasText(partnerschap.getAangaanHuwelijkPartnerschapLand()) ? PersoonConverterImpl.calcLand(partnerschap.getAangaanHuwelijkPartnerschapLand()) : null);
        return result;
    }

}

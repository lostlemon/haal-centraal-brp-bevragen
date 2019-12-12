package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Afstamming;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.DatumOnvolledig;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderLinks;
import nl.vng.realisatie.haalcentraal.web.Util;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;


/**
 * OuderConverterImpl
 */
@Service
public class OuderConverterImpl extends BaseConverterImpl<OuderHal, Afstamming> implements OuderConverter {

    @Inject
    private NaamConverter naamConverter;

    @Inject
    private GeboorteConverter geboorteConverter;

    public OuderConverterImpl() {
        getMap().put("burgerservicenummer", (ouderHal, afstamming, fields, expand) -> ouderHal.setBurgerservicenummer(afstamming.getOuder().getBsn() != null ? Util.formatBsn.apply(afstamming.getOuder().getBsn()) : null));
        getMap().put("geboorte", (ouderHal, afstamming, fields, expand) -> ouderHal.setGeboorte(geboorteConverter.convert(afstamming.getOuder(), fields, expand)));
        getMap().put("geslachtsaanduiding", (ouderHal, afstamming, fields, expand) -> ouderHal.setGeslachtsaanduiding(PersoonConverterImpl.calcGeslachstaanduiding(afstamming.getOuder())));
        getMap().put("ouder_aanduiding", (ouderHal, afstamming, fields, expand) -> ouderHal.setOuderAanduiding(afstamming.getOuderAanduidingEnum()));
        getMap().put("datumIngangFamilierechtelijkeBetrekking", (ouderHal, afstamming, fields, expand) -> ouderHal.setDatumIngangFamilierechtelijkeBetrekking(calcDatumIngangFamilierechtelijkeBetrekking(afstamming)));
        getMap().put("naam", (ouderHal, afstamming, fields, expand) -> ouderHal.setNaam(naamConverter.convert(afstamming.getOuder(), fields, expand)));
        getMap().put("ingeschrevenpersonen", (ouderHal, afstamming, fields, expand) -> ouderHal.getLinks().setIngeschrevenPersoon(PersoonConverterImpl.createIngeschrevenPersoonLink(afstamming.getOuder().getBsn(), expand, null)));
    }

    public static DatumOnvolledig calcDatumIngangFamilierechtelijkeBetrekking(final Afstamming afstamming) {
        return PersoonConverterImpl.convertDatum(afstamming.getDatumIngangFamilierechtelijkeBetrekking()).orElse(null);
    }

    @Override
    public OuderHal createTarget(final Afstamming afstamming, final Set<String> fields) {
        final OuderHal ouderHal = new OuderHal();
        ouderHal.setLinks(calcLinks(afstamming));
        return ouderHal;
    }

    private OuderLinks calcLinks(final Afstamming afstamming) {
        final OuderLinks ouderLinks = new OuderLinks();
        ouderLinks.setSelf(PersoonConverterImpl.createOuderLink.apply(afstamming));
        return ouderLinks;
    }

}

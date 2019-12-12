package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Afstamming;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.KindHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.KindLinks;
import nl.vng.realisatie.haalcentraal.web.Util;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;

/**
 * KindConverterImpl
 */
@Service
public class KindConverterImpl extends BaseConverterImpl<KindHal, Afstamming> implements KindConverter {

    @Inject
    private NaamConverter naamConverter;

    @Inject
    private GeboorteConverter geboorteConverter;

    @Inject
    private LeeftijdService leeftijdService;

    public KindConverterImpl() {
        getMap().put("burgerservicenummer", (kindHal, afstamming, fields, expand) -> kindHal.setBurgerservicenummer(Util.formatBsn.apply(afstamming.getKind().getBsn())));
        getMap().put("geboorte", (kindHal, afstamming, fields, expand) -> kindHal.setGeboorte(geboorteConverter.convert(afstamming.getKind(), fields, expand)));
        getMap().put("leeftijd", (kindHal, afstamming, fields, expand) -> kindHal.setLeeftijd(leeftijdService.calcLeeftijd(afstamming.getKind()).orElse(null)));
        getMap().put("naam", (kindHal, afstamming, fields, expand) -> kindHal.setNaam(naamConverter.convert(afstamming.getKind(), fields, expand)));
//        getMap().put("thuiswonend", (kindHal, afstamming, fields, expand) -> kindHal.setThuiswonend(calcThuisWonend(afstamming)));
        getMap().put("ingeschrevenpersonen", (kindHal, afstamming, fields, expand) -> kindHal.getLinks().setIngeschrevenPersoon(PersoonConverterImpl.createIngeschrevenPersoonLink(afstamming.getKind().getBsn(), expand, null)));
    }

    @Override
    public KindHal createTarget(final Afstamming afstamming, final Set<String> fields) {
        final KindHal kindHal = new KindHal();
        kindHal.setLinks(calcLinks(afstamming));
        return kindHal;
    }

    private KindLinks calcLinks(final Afstamming afstamming) {
        final KindLinks kindLinks = new KindLinks();
        kindLinks.setSelf(PersoonConverterImpl.createKindLink.apply(afstamming));
        return kindLinks;
    }

//    private Boolean calcThuisWonend(final Afstamming afstamming) {
//        final QVerblijfplaats qVerblijfplaats = new QVerblijfplaats("verblijfplaats");
//        final Verblijfplaats v_kind = verblijfplaatsRepository.findOne(qVerblijfplaats.persoon.eq(afstamming.getKind())).orElse(null);
//        final Verblijfplaats v_ouder = verblijfplaatsRepository.findOne(qVerblijfplaats.persoon.eq(afstamming.getOuder())).orElse(null);
//        boolean result;
//        if (v_kind != null && v_kind.getId() != null && v_ouder != null && v_ouder.getId() != null) {
//            result = true;
//        } else {
//            result = false;
//        }
//        return result;
//    }

}

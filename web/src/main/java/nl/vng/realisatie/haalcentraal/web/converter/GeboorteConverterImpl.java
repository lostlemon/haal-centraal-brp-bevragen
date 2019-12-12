package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Geboorte;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.GeboorteInOnderzoek;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;

/**
 * GeboorteConverterImpl
 */
@Service
public class GeboorteConverterImpl extends BaseConverterImpl<Geboorte, Persoon> implements GeboorteConverter {

    @Inject
    private GeboorteDatumConverter geboorteDatumConverter;

    public GeboorteConverterImpl() {
        getMap().put("datum", (geboorte, persoon, fields, expand) -> geboorte.setDatum(geboorteDatumConverter.convert(persoon, fields, expand)));
        getMap().put("land", (geboorte, persoon, fields, expand) -> geboorte.setLand(calcLand(persoon.getGeboorteLand())));
        getMap().put("plaats", (geboorte, persoon, fields, expand) -> geboorte.setPlaats(calcPlaats(persoon.getGeboortePlaats())));
    }

    @Override
    public Geboorte createTarget(final Persoon source, final Set<String> fields) {
        return new Geboorte();
    }

    @Override
    public Geboorte postConvert(Persoon source, Geboorte result, Set<String> fields) {

        if (Boolean.TRUE.equals(source.getInOnderzoekGeboorteDatum()) || Boolean.TRUE.equals(source.getInOnderzoek())) {
            if (result.getInOnderzoek() == null) {
                result.setInOnderzoek(new GeboorteInOnderzoek());
            }
            result.getInOnderzoek().setDatum(true);
        }

        if (Boolean.TRUE.equals(source.getInOnderzoekGeboorteLand()) || Boolean.TRUE.equals(source.getInOnderzoek())) {
            if (result.getInOnderzoek() == null) {
                result.setInOnderzoek(new GeboorteInOnderzoek());
            }
            result.getInOnderzoek().setLand(true);
        }

        if (Boolean.TRUE.equals(source.getInOnderzoekGeboortePlaats()) || Boolean.TRUE.equals(source.getInOnderzoek())) {
            if (result.getInOnderzoek() == null) {
                result.setInOnderzoek(new GeboorteInOnderzoek());
            }
            result.getInOnderzoek().setPlaats(true);
        }
        return result;
    }

}

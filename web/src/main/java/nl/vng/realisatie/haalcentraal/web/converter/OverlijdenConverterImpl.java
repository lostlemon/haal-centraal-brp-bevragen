package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Overlijden;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;

/**
 * OverlijdenConverterImpl
 */
@Service
public class OverlijdenConverterImpl extends BaseConverterImpl<Overlijden, Persoon> implements OverlijdenConverter {

    @Inject
    private GeboorteDatumConverter geboorteDatumConverter;

    public OverlijdenConverterImpl() {
        getMap().put("datum", (overlijden, persoon, fields, expand) -> overlijden.setDatum(geboorteDatumConverter.convert(persoon, fields, expand)));
        getMap().put("land", (overlijden, persoon, fields, expand) -> overlijden.setLand(calcLand(persoon.getOverlijdenLand())));
        getMap().put("plaats", (overlijden, persoon, fields, expand) -> overlijden.setPlaats(calcPlaats(persoon.getOverlijdenPlaats())));
    }

    @Override
    public Overlijden createTarget(final Persoon source, final Set<String> fields) {
        return new Overlijden();
    }

}

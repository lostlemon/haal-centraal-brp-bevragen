package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.DatumOnvolledig;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

/**
 * GeboorteDatumConverterImpl
 */
@Service
public class GeboorteDatumConverterImpl extends BaseConverterImpl<DatumOnvolledig, Persoon> implements GeboorteDatumConverter {

    public GeboorteDatumConverterImpl() {
        getMap().put("jaar", (datum, persoon, fields, expand) -> datum.setJaar(convertDatum0ToInteger.apply(persoon.getGeboorteDatum().substring(0, 4))));
        getMap().put("dag", (datum, persoon, fields, expand) -> datum.setDag(convertDatum0ToInteger.apply(persoon.getGeboorteDatum().substring(6, 8))));
        getMap().put("maand", (datum, persoon, fields, expand) -> datum.setMaand(convertDatum0ToInteger.apply(persoon.getGeboorteDatum().substring(4, 6))));
        getMap().put("datum", (datum, persoon, fields, expand) -> datum.setDatum(convertLocalDatum(persoon.getGeboorteDatum()).orElse(null)));
    }

    private static Function<String, Integer> convertDatum0ToInteger = s -> {
        final Integer result = Integer.valueOf(s);
        return result == 0 ? null : result;
    };

    @Override
    public DatumOnvolledig createTarget(final Persoon source, final Set<String> fields) {
        return Integer.parseInt(source.getGeboorteDatum()) != 0 ? new DatumOnvolledig() : null;
    }

}

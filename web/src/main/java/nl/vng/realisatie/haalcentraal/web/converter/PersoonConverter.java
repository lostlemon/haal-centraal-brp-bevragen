package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonHal;

import java.util.Set;

/**
 * PersoonConverter
 */
public interface PersoonConverter extends BaseConverter<IngeschrevenPersoonHal, Persoon> {

    void handleExpand(final IngeschrevenPersoonHal ingeschrevenPersoon, final Persoon persoon, Set<String> expand);

}

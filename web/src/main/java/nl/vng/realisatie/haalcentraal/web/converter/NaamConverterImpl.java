package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Naam;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * NaamConverterImpl
 */
@Service
public class NaamConverterImpl extends BaseConverterImpl<Naam, Persoon> implements NaamConverter {

    @SuppressWarnings("CPD-START")
    public NaamConverterImpl() {
        getMap().put("voorletter", (naam, persoon, fields, expand) -> naam.setVoorletters(calcVoorletters(persoon.getVoornamen())));
        getMap().put("voornamen", (naam, persoon, fields, expand) -> naam.setVoornamen(persoon.getVoornamen()));
        getMap().put("geslachtsnaam", (naam, persoon, fields, expand) -> naam.setGeslachtsnaam(persoon.getGeslachtsnaam()));
        getMap().put("voorvoegsel", (naam, persoon, fields, expand) -> naam.setVoorvoegsel(persoon.getVoorvoegsel()));
    }

    @Override
    public Naam createTarget(final Persoon source, final Set<String> fields) {
        return new Naam();
    }

}

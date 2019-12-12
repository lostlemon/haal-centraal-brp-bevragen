package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * EnumWaardeStepDefinitions
 */

@Slf4j
public class EnumWaardeStepDefinitions extends Defaults {

    final static Map<String, String> givenMapping = new HashMap<>();

    static {
        givenMapping.put("1", "999992089");
        givenMapping.put("2", "999990408");
        givenMapping.put("12", "999992144");
        givenMapping.put("D", "999990640");
        givenMapping.put("1D", "999991383");
        givenMapping.put("2D", "999991577");
    }

    @Gegeven("element {string} van een ingeschreven persoon bevat de waarde {word}")
    public void element_van_een_ingeschreven_persoon_bevat_de_waarde(String element, String enum_waarde) throws URISyntaxException {
        ingeschrevenNatuurlijkPersoon(givenMapping.get(enum_waarde), null, null);
    }

    @Als("de ingeschreven persoon wordt geraadpleegd")
    public void de_ingeschreven_persoon_wordt_geraadpleegd() {
        assertThat(world.persoonHal, is(notNullValue()));
    }

    @Dan("is indicatieGezagMinderjarige van de ingeschreven persoon gelijk aan {}")
    public void is_indicatieGezagMinderjarige_van_de_ingeschreven_persoon_gelijk_aan_ouder(String enumWaarde) {
        assertTrue(world.persoonHal.getGezagsverhouding().getIndicatieGezagMinderjarige().getValue().equals(enumWaarde));
    }

}

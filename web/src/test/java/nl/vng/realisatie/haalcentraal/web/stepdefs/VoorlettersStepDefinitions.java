package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.web.controller.BevraagPersoonController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * VoorlettersStepDefinitions
 */
@Slf4j
public class VoorlettersStepDefinitions extends Defaults {

    private static final AtomicInteger bsnCounter = new AtomicInteger(100000000);

    @Gegeven("een ingeschreven persoon met voornamen {}")
    public void een_ingeschreven_persoon_met_voornamen(String voornamen) throws ClassNotFoundException, SQLException {
        testDataController.insertIntoPersoon("" + bsnCounter.getAndIncrement(), voornamen, "Extrapersoon", "20190916", "20190916");
    }

    @Als("de persoon met voornaam {string} wordt geraadpleegd")
    public void de_persoon_wordt_geraadpleegd_met_voornamen(String voornamen) throws URISyntaxException, UnsupportedEncodingException {
        final LocalDate date = LocalDate.parse("2019-09-16", BevraagPersoonController.QUERYFORMATTER);
        world.persoonCollectie = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersonen(null, null, null, null, date, null, null, null, "Extrapersoon", voornamen, null, null, null, null, null, null, null, null);
    }

    @Dan("zijn de voorletters van de ingeschreven persoon gelijk aan {}")
    public void zijn_de_voorletters_van_de_ingeschreven_persoon_gelijk_aan_voorletters(String voorletters) {
        assertThat(world.persoonCollectie.getEmbedded().getIngeschrevenpersonen().size(), is(1));
        assertThat(world.persoonCollectie.getEmbedded().getIngeschrevenpersonen().stream().findFirst().get().getNaam().getVoorletters(), is(voorletters));
    }

}

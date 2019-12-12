package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HappyStepDefinitions
 */
@Slf4j
public class HappyStepDefinitions extends Defaults {

    public HappyStepDefinitions() {
        log.debug("HappyStepDefinitions()");
    }

    @Als("Er persoon gezocht word met bsn {string}")
    public void er_persoon_gewzocht_word_met_bsn(String bsn) throws ClientProtocolException, IOException, URISyntaxException {
        ingeschrevenNatuurlijkPersoon(bsn, null, null);
    }

    @Dan("vind ik een persoon met bsn {string}")
    public void vind_ik_een_persoon_met_bsn(String bsn) {
        assertThat(world.persoonHal.getBurgerservicenummer(), is(bsn));
    }

}

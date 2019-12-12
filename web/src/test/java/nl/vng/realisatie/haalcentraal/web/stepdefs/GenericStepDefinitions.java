package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * GenericStepDefinitions
 */
@Slf4j
public class GenericStepDefinitions extends Defaults {

    @Dan("is in elke van de gevonden partners attribuut {word} niet aanwezig of null")
    public void is_in_elke_van_de_gevonden_partners_attribuut_niet_aanwezig_of_null(final String attribuut) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is in elke van de gevonden ingeschrevenpersonen attribuut {word} niet aanwezig of null")
    public void is_in_elke_van_de_gevonden_ingeschrevenpersonen_attribuut__niet_aanwezig_of_null(final String attribuut) {

        assertThat(world.persoonCollectie.getEmbedded().getIngeschrevenpersonen().size(), is(Matchers.greaterThan(0)));

        switch (attribuut) {
            case "overlijden.datum.datum": {
                assertThat(world.persoonCollectie.getEmbedded().getIngeschrevenpersonen().stream()
                                .allMatch(p -> p.getOverlijden() == null || p.getOverlijden().getDatum() == null)
                        , is(true));
                break;
            }
            default: {
                throw new IllegalArgumentException("onbekend attribuut: " + attribuut);
            }
        }
    }

    @En("heeft de foutmelding betrekking op parameter {word}")
    public void heeftDeFoutmeldingBetrekkingOpParameterExpand(String parameter) {
        org.assertj.core.api.Assertions.assertThat(world.validatieFoutbericht.getInvalidParams())
                .anyMatch(p -> p.getName().equals(parameter));
    }

}

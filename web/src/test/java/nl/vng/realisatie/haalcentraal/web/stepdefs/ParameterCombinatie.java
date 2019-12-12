package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Dan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Slf4j
public class ParameterCombinatie extends Defaults {

    @Dan("is het resultaat zoekresultaten")
    public void is_het_resultaat_zoekresultaten() {
        assertThat(world.persoonCollectie, is(notNullValue()));
        assertThat(world.statusCode, is(HttpStatus.OK.value()));
    }

}

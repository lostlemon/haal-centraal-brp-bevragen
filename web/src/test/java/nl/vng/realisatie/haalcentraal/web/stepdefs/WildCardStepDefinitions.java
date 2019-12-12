package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.DatumOnvolledig;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.web.controller.BevraagPersoonController;
import org.hamcrest.CoreMatchers;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * WildCardStepDefinitions
 */
@Slf4j
public class WildCardStepDefinitions extends Defaults {

    @Dan("levert dit een foutmelding")
    public void levert_dit_een_foutmelding() {
        is_het_resultaat_een_foutmelding();
    }

    @Dan("is het resultaat een foutmelding")
    public void is_het_resultaat_een_foutmelding() {
        assertThat(world.statusCode, CoreMatchers.is(not(HttpStatus.OK.value())));
    }

    @Als("ingeschreven personen gezocht worden met {}")
    public void ingeschreven_personen_gezocht_worden_met(String zoekstring) throws IOException, URISyntaxException {
        callRestTemplate(zoekstring);
    }

    @Dan("wordt de ingeschreven persoon gevonden met {word}={}")
    public void wordt_de_ingeschreven_persoon_gevonden_met(String attribute, String value) throws IOException {

        final Function<IngeschrevenPersoonHal, Optional<DatumOnvolledig>> overLijdenDatum = (p) -> {
            if (p.getOverlijden() == null || p.getOverlijden().getDatum() == null) {
                return Optional.empty();
            } else {
                return Optional.of(p.getOverlijden().getDatum());
            }
        };

        if (world.persoonCollectie == null || world.persoonCollectie.getEmbedded() == null) {
            fail("persoonCollectie.getBody().getEmbedded() == null");
        }

        final List<IngeschrevenPersoonHal> list = world.persoonCollectie.getEmbedded().getIngeschrevenpersonen();

        if ("overlijden.datum.datum".equals(attribute)) {

            final Stream<Optional<DatumOnvolledig>> dates = list.stream().map(overLijdenDatum);

            if ("null".equalsIgnoreCase(value)) {
                assertTrue(dates.anyMatch((p) -> p.isEmpty()));
            } else {
                TemporalAccessor parsed = BevraagPersoonController.QUERYFORMATTER.parse(value);
                handleOverlijdenDatum(dates, parsed);
            }
        } else {

            final Map<String, Function<IngeschrevenPersoonHal, String>> repo = new HashMap<>();

            repo.put("naam.geslachtsnaam", (p) -> p.getNaam().getGeslachtsnaam());
            repo.put("naam.voorvoegsel", (p) -> p.getNaam().getVoorvoegsel());
            repo.put("naam.voornamen", (p) -> p.getNaam().getVoornamen());
            repo.put("verblijfplaats.naamOpenbareRuimte", ((p) -> p.getVerblijfplaats().getNaamOpenbareRuimte()));
            repo.put("verblijfplaats.huisletter", (p) -> p.getVerblijfplaats().getHuisletter());
            repo.put("geboorte.plaats", (p) -> p.getGeboorte().getPlaats().getOmschrijving());

            if (!repo.containsKey(attribute)) {
                throw new IllegalArgumentException(attribute + " not implemented, value=" + value);
            }

            final Stream<String> actual = list.stream().map(repo.get(attribute));

            assertTrue(actual.map(String::toLowerCase).anyMatch(p -> p.contains(value.toLowerCase())));
        }
    }

    public void handleOverlijdenDatum(final Stream<Optional<DatumOnvolledig>> actual, final TemporalAccessor temporalAccessor) {
        assertTrue(actual.filter(Optional::isPresent).anyMatch((p) -> datumOnvolledigEquals(p.get(), temporalAccessor)));
    }

    public static Boolean datumOnvolledigEquals(final DatumOnvolledig p, final TemporalAccessor value) {

        return p.getDag().equals(value.get(ChronoField.DAY_OF_MONTH))
                && p.getJaar().equals(value.get(ChronoField.YEAR))
                && p.getMaand().equals(value.get(ChronoField.MONTH_OF_YEAR));
    }

    @Dan("voldoet elke van de gevonden ingeschrevenpersonen aan de query {word}={}")
    public void voldoet_elke_van_de_gevonden_ingeschrevenpersonen_aan_de_query(final String attribute, final String query) throws IOException, URISyntaxException {
        final Function<IngeschrevenPersoonHal, String> geslachtsNaam = (p) -> p.getNaam().getGeslachtsnaam();
        final Function<IngeschrevenPersoonHal, String> voorNamen = (p) -> p.getNaam().getVoornamen();

        if (world.persoonCollectie == null || world.persoonCollectie.getEmbedded() == null) {
            fail("persoonCollectie.getBody().getEmbedded() == null");
        }
        final List<IngeschrevenPersoonHal> list = world.persoonCollectie.getEmbedded().getIngeschrevenpersonen();

        final Map<String, Function<Stream<IngeschrevenPersoonHal>, Stream<String>>> repo = new HashMap<>();

        repo.put("naam.geslachtsnaam", (s) -> s.map(geslachtsNaam));
        repo.put("naam.voornamen", (s) -> s.map(voorNamen));

        if (!repo.containsKey(attribute)) {
            throw new IllegalArgumentException(attribute + " not implemented");
        }
        final Stream<String> actual = repo.get(attribute).apply(list.stream()).map(String::toLowerCase);
        final String value = query.replace("*", "").toLowerCase();
        if (query.contains("*")) {
            if (query.contains("*")) {
                assertTrue(actual.allMatch(s -> s.contains(value)));
            } else {
                if (query.startsWith("*")) {
                    assertTrue(actual.allMatch(s -> s.endsWith(value)));
                } else if (query.endsWith("*")) {
                    assertTrue(actual.allMatch(s -> s.startsWith(value)));
                }
            }
        }
        if (query.contains("?")) {
            assertTrue(actual.allMatch(s -> s.length() == value.length()));
        }
    }

    @Gegeven("de registratie ingeschreven personen kent met de naamopenbareruimte {string}, {string}")
    public void de_registratie_ingeschreven_personen_kent_met_de_naamopenbareruimte(String string, String string2) {
        // hoeft geen data geinsert te worden, want zit al in de database
    }

    @Dan("wordt geen ingeschreven persoon gevonden met {word}={}")
    public void wordt_geen_ingeschreven_persoon_gevonden_met_naam_voornamen(String attribute, String query) {
        List<IngeschrevenPersoonHal> resultList;
        final Function<IngeschrevenPersoonHal, String> geslachtsNaam = (p) -> p.getNaam().getGeslachtsnaam();
        final Function<IngeschrevenPersoonHal, String> voorNamen = (p) -> p.getNaam().getVoornamen();
        if (world.persoonCollectie == null || world.persoonCollectie.getEmbedded() == null) {
            fail("persoonCollectie.getBody().getEmbedded() == null");
        }
        final List<IngeschrevenPersoonHal> list = world.persoonCollectie.getEmbedded().getIngeschrevenpersonen();

        final Map<String, Function<Stream<IngeschrevenPersoonHal>, Stream<String>>> repo = new HashMap<>();

        repo.put("naam.geslachtsnaam", (s) -> s.map(geslachtsNaam));
        repo.put("naam.voornamen", (s) -> s.map(voorNamen));

        if (!repo.containsKey(attribute)) {
            throw new IllegalArgumentException(attribute + " not implemented");
        }
        final Stream<String> actual = repo.get(attribute).apply(list.stream()).map(String::toLowerCase);
        assertTrue(actual.noneMatch(s -> s.equalsIgnoreCase(query)));
    }

    @Dan("levert dit zoekresultaten")
    public void levert_dit_zoekresultaten() {
        assertThat(world.persoonCollectie.getEmbedded(), is(not(nullValue())));
        assertThat(world.persoonCollectie.getEmbedded().getIngeschrevenpersonen(), is(not(nullValue())));
    }

    @Dan("is het aantal gevonden ingeschrevenpersonen {int}")
    public void is_het_aantal_gevonden_ingeschrevenpersonen(Integer size) {
        assertThat(world.persoonCollectie.getEmbedded().getIngeschrevenpersonen(), hasSize(size));
    }

}

package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * LeeftijdBepalingStepDefinitions
 */
@Slf4j
public class LeeftijdBepalingStepDefinitions extends Defaults {

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("d MMMM yyyy").toFormatter(new Locale("nl", "NL"));

    @Gegeven("een ingeschreven persoon met geboortedatum {} {word} {}")
    public void een_ingeschreven_persoon_met_geboortedatum(String dag, String maand, String jaar) throws SQLException, ClassNotFoundException {
        final String defaultWaarde = "00";
        if (StringUtils.isEmpty(dag)) {
            dag = defaultWaarde;
        }
        if (StringUtils.isEmpty(maand)) {
            maand = defaultWaarde;
        }
        final StringJoiner paramSequence = new StringJoiner(" ").add(dag).add(maand).add(jaar).setEmptyValue("00000000");
        final String stringDatum = paramSequence.toString();
        LocalDate datum = LocalDate.parse(stringDatum, formatter);
        final String geboorteDatum = datum.format(DateTimeFormatter.BASIC_ISO_DATE);
        testDataController.insertIntoPersoon("100000001", "Bob", "Extrapersoon", "20190916", geboorteDatum);
    }

    @Als("de ingeschreven persoon op {} wordt geraadpleegd")
    public void de_ingeschreven_persoon_op_raadpleeg_datum_wordt_geraadpleegd(final String raadpleegDatum) {
        LocalDate datum = LocalDate.parse(raadpleegDatum, formatter);
        serviceConfig.setLocalDate(datum);
        world.persoonHal = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersoon("100000001", null, null, null);
    }

    @Gegeven("een ingeschreven persoon kent geen geboortedatum")
    public void een_ingeschreven_persoon_kent_geen_geboortedatum() throws SQLException, ClassNotFoundException {
        testDataController.insertIntoPersoon("100000001", "Bob", "Extrapersoon", "20190916", "00000000");
    }

    @Als("de ingeschreven persoon met onbekend geboortedatum wordt geraadpleegd")
    public void de_ingeschreven_persoon_met_onbekend_geboortedatum_wordt_geraadpleegd() {
        world.persoonHal = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersoon("100000001", null, null, null);
    }

    @Dan("is attribuut leeftijd niet aanwezig")
    public void is_attribuut_leeftijd_niet_aanwezig() {
        assertThat(world.persoonHal.getLeeftijd(), is(nullValue()));
    }

    @Gegeven("een ingeschreven persoon met geboortedatum mei 1983")
    public void een_ingeschreven_persoon_met_geboortedatum_mei_1983() throws SQLException, ClassNotFoundException {
        testDataController.insertIntoPersoon("100000001", "Bob", "Extrapersoon", "20190916", "19830500");
    }

    @Gegeven("een ingeschreven persoon met geboortedatum 1983")
    public void een_ingeschreven_persoon_met_geboortedatum_1983() throws SQLException, ClassNotFoundException {
        testDataController.insertIntoPersoon("100000001", "Bob", "Extrapersoon", "20190916", "19830500");
    }

    @Gegeven("de ingeschreven persoon is overleden")
    public void de_ingeschreven_persoon_is_overleden() {
        testDataController.updateOverlijden("100000001", "20181225");
    }

}

package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import io.cucumber.java.nl.Gegeven;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.DatumOnvolledig;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.Geboorte;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.OuderHal;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * OnvolledigeDatumStepDefinitions
 */
public class OnvolledigeDatumStepDefinitions extends Defaults {
    public final static DateTimeFormatter DATE_FORMATTER_DASH_SPLITTED = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();

    @Gegeven("de registratie ingeschreven persoon {int} kent met geboortedatum {} \\({int})")
    public void de_registratie_ingeschreven_persoon_kent_met_geboortedatum_mei(Integer bsn, String date, Integer dateString) {
        // hoeft niet te implementeren, data is al aanwezig
    }

    @Als("de ingeschreven persoon met burgerservicenummer {word} wordt geraadpleegd")
    public void de_ingeschreven_persoon_met_burgerservicenummer_wordt_geraadpleegd(String bsn) {
        ingeschrevenNatuurlijkPersoon(bsn, null, null);
    }

    @Dan("heeft attribuut {} de waarde {}")
    public void heeft_attribuut_geboorte_datum_datum_de_waarde(String field, String value) {
        String[] splittedField = StringUtils.split(field, ".");
        String subField = splittedField[splittedField.length - 1];
        String superCategory = splittedField[0];
        switch (superCategory) {
            case "geboorte":
                DatumOnvolledig geboorteDatum = world.persoonHal.getGeboorte().getDatum();
                switch (subField) {
                    case "dag":
                        assertTrue(geboorteDatum.getDag().equals(Integer.valueOf(value)));
                        break;
                    case "maand":
                        assertTrue(geboorteDatum.getMaand().equals(Integer.valueOf(value)));
                        break;
                    case "jaar":
                        assertTrue(geboorteDatum.getJaar().equals(Integer.valueOf(value)));
                        break;
                    case "datum":
                        LocalDate expectedDate = LocalDate.from(DATE_FORMATTER_DASH_SPLITTED.parse(value));
                        assertThat(geboorteDatum.getDatum(), is(expectedDate));
                        break;
                }
            case "overlijden":
                if (subField == "indicatieOverleden") {
                    assertThat(world.persoonHal.getOverlijden().getIndicatieOverleden(), is(value));
                } else if (subField == "datum") {
                    assertThat(world.persoonHal.getOverlijden(), is(notNullValue()));
                    assertThat(world.persoonHal.getOverlijden().getDatum().getDatum(), is(convertDatum(value)));
                }
                break;
            case "leeftijd": {
                if (value.equals("<niet aanwezig>")) {
                    assertThat(world.persoonHal.getLeeftijd(), is(nullValue()));
                } else {
                    assertThat(world.persoonHal.getLeeftijd(), is(Integer.valueOf(value)));
                }
                break;
            }
        }
    }


    @En("is in het antwoord attribuut {word} niet aanwezig")
    public void isInHetAntwoordAttribuut_embeddedOudersNietAanwezig(String field) {
        is_in_het_antwoord_x_niet_aanwezig(field);
    }

    @En("is in het antwoord attribuut {word} niet aanwezig of null")
    public void isInHetAntwoordAttribuut_embeddedOudersNietAanwezigOfnull(String field) {
        is_in_het_antwoord_x_niet_aanwezig(field);
    }

    @Dan("is in het antwoord {word} niet aanwezig of null")
    public void danIsInHetAntwoordAttribuut_embeddedOudersNietAanwezigOfnull(String field) {
        is_in_het_antwoord_x_niet_aanwezig(field);
    }

    @Dan("is in het antwoord {word} niet aanwezig")
    public void danIsInHetAntwoordAttribuut_embeddedOudersNietAanwezig(String field) {
        is_in_het_antwoord_x_niet_aanwezig(field);
    }

    @En("is in het antwoord attribuut {word} afwezig")
    public void isInHetAntwoordAttribuut_embeddedOudersAfwezig(String field) {
        is_in_het_antwoord_x_niet_aanwezig(field);
    }

    @En("is in het antwoord attribuut {word} null, leeg of afwezig")
    public void isInHetAntwoordAttribuut_embeddedOudersAfwezigOfNull(String field) {
        is_in_het_antwoord_x_niet_aanwezig(field);
    }

    public void is_in_het_antwoord_x_niet_aanwezig(String field) {
        switch (field) {
            case "geboorte.plaats":
                assertThat(world.persoonHal.getGeboorte().getPlaats(), is(nullValue()));
                break;
            case "inOnderzoek":
                assertThat(world.persoonHal.getInOnderzoek(), is(nullValue()));
                break;
            case "naam.inOnderzoek.voorvoegsel":
                assertThat(world.persoonHal.getNaam().getInOnderzoek().getVoorvoegsel(), is(nullValue()));
                break;
            case "naam.inOnderzoek":
                if (world.persoonHal.getNaam() != null) {
                    assertThat(world.persoonHal.getNaam().getInOnderzoek(), is(nullValue()));
                }
                break;
            case "naam.inOnderzoek.geslachtsnaam":
                assertThat(world.persoonHal.getNaam().getInOnderzoek().getGeslachtsnaam(), is(nullValue()));
                break;
            case "geboorte.datum":
                assertThat(world.persoonHal.getGeboorte().getDatum(), is(nullValue()));
                break;
            case "overlijden":
                assertThat(world.persoonHal.getOverlijden(), is(nullValue()));
                break;
            case "overlijden.datum":
                assertThat(world.persoonHal.getOverlijden().getDatum(), is(nullValue()));
                break;
            case "geboorte.datum.datum":
                assertThat(world.persoonHal.getGeboorte().getDatum().getDatum(), is(nullValue()));
                break;
            case "geboorte.datum.dag":
                if (world.persoonHal != null) {
                    assertThat(world.persoonHal.getGeboorte().getDatum().getDag(), is(nullValue()));
                } else if (world.ouderHalCollectie != null) {
                    List<Integer> dagen = world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getGeboorte).map(Geboorte::getDatum).map(DatumOnvolledig::getDag).collect(Collectors.toList());
                    assertThat(dagen.stream().allMatch(Objects::isNull), is(true));
                }
                break;
            case "geboorte.datum.maand":
                assertThat(world.persoonHal.getGeboorte().getDatum().getMaand(), is(nullValue()));
                break;
            case "geboorte.datum.jaar":
                assertThat(world.persoonHal.getGeboorte().getDatum().getJaar(), is(nullValue()));
                break;
            case "geboorte.inOnderzoek":
                if (world.persoonHal.getGeboorte() != null) {
                    assertThat(world.persoonHal.getGeboorte().getInOnderzoek(), is(nullValue()));
                }
                break;
            case "geboorte.inOnderzoek.plaats":
                assertThat(world.persoonHal.getGeboorte().getInOnderzoek().getPlaats(), is(nullValue()));
                break;
            case "_embedded":
                assertThat(world.persoonHal.getEmbedded(), is(nullValue()));
                break;
            case "_embedded.ouders":
                assertThat(world.persoonHal.getEmbedded().getOuders(), is(nullValue()));
                break;
            case "_embedded.partners":
                assertThat(world.persoonHal.getEmbedded().getPartners(), is(nullValue()));
                break;
            case "_embedded.kinderen":
                assertThat(world.persoonHal.getEmbedded().getKinderen(), is(nullValue()));
                break;
            default:
                throw new IllegalArgumentException("Onbekend field: " + field);
        }
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent geen geboortedatum \\({int})")
    public void de_registratie_ingeschreven_persoon_kent_geen_geboortedatum(Integer int1, Integer int2) {
        // hoeft niet te implementeren, data is al aanwezig
    }

    @Gegeven("de registratie ingeschreven persoon {int} heeft een volledig onbekende overlijdensdatum \\({int})")
    public void de_registratie_ingeschreven_persoon_heeft_een_volledig_onbekende_overlijdensdatum(Integer bsn, Integer overlijdenDatum) {
        updatePersoon(bsn, String.valueOf(overlijdenDatum));
    }

    private void updatePersoon(final Integer bsn, final String overlijdenDatum) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{overlijdenDatum};
        final int[] types = new int[]{Types.VARCHAR};
        final String query = "UPDATE Persoon p SET overlijdenDatum = ? WHERE bsn = " + bsn;
        template.update(query, params, types);
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent geen overlijdensdatum")
    public void de_registratie_ingeschreven_persoon_kent_geen_overlijdensdatum(Integer int1) {
        // hoeft niet te implementeren, data is al aanwezig
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent een overlijdensdatum \\({int})")
    public void de_registratie_ingeschreven_persoon_kent_een_overlijdensdatum(Integer int1, Integer int2) {
        // hoeft niet te implementeren, data is al aanwezig
    }

    @Dan("heeft in het antwoord overlijden.datum.datum de waarde {int}{int}{int}")
    public void heeft_in_het_antwoord_overlijden_datum_datum_de_waarde(Integer int1, Integer int2, Integer int3) {

    }

}

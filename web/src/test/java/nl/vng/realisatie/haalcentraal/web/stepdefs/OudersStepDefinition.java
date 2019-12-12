package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.enums.AdellijkeTitelPredikaat;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.OuderAanduidingEnum;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.OuderHal;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * OudersStepDefinition
 */
@Slf4j
public class OudersStepDefinition extends Defaults {

    public final static String INGANG_DATUM = "99999999";
    private IngeschrevenPersoonHal ouder = null;
    private OuderHal ouderHal = null;
    public final static String GEBOORTE_DATUM = "19720831";
    final static Integer nullBsn = 100000000;

    @Gegeven("de te raadplegen persoon heeft een ouder die zelf ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_een_ouder_die_zelf_ingeschreven_persoon_is() {
    }

    @Gegeven("de te raadplegen persoon heeft een ouder die zelf geen ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_een_ouder_die_zelf_geen_ingeschreven_persoon_is() {
    }

    @Gegeven("de te raadplegen persoon heeft geen geregistreerde ouders")
    public void de_te_raadplegen_persoon_heeft_geen_geregistreerde_ouders() {
        ingeschrevenNatuurlijkPersoonOuders("999999291");
    }

    @Gegeven("de Ouder{int} van de ingeschreven persoon heeft in de registratie burgerservicenummer {int}, naam {}")
    public void de_Ouder_van_de_ingeschreven_persoon_heeft_in_de_registratie_burgerservicenummer_naam_Jonkheer_Franciscus_Theodorus_in_t_Groen(Integer ouderNum, Integer bsn, String naam) {
        if (ouderNum == 1) {
            insertInAfstamming(4L, 47L, OuderAanduidingEnum.OUDER1.name(), INGANG_DATUM);
        } else {
            insertInAfstamming(4L, 48L, OuderAanduidingEnum.OUDER2.name(), INGANG_DATUM);
        }
    }

    @Dan("wordt de ouder gevonden met {}")
    public void wordt_de_ouder_gevonden_met(String field) {
        String[] keyValue = field.split("=");
        final String key = keyValue[0];
        final String value = keyValue[1];
        assertThat(world.ouderHalCollectie.getEmbedded(), is(notNullValue()));
        assertThat(world.ouderHalCollectie.getEmbedded().getOuders(), is(notNullValue()));
        if (key.equals("ouder_aanduiding")) {

            ouderHal = world.ouderHalCollectie.getEmbedded().getOuders()
                    .stream()
                    .filter(o -> o.getOuderAanduiding().getValue().contains(value))
                    .findFirst().orElse(null);
            assertThat(ouderHal, is(notNullValue()));
            final String ouderAanduidingValue = ouderHal.getOuderAanduiding().getValue();
            final String actuel = ouderAanduidingValue.substring(ouderAanduidingValue.indexOf(value));
            assertThat(actuel, is(value));
            ouder = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersoon(ouderHal.getBurgerservicenummer(), null, null, null);
            assertThat(ouder, is(notNullValue()));
        }
        if (key.equals("naam.voornamen")) {
            assertThat(world.ouderHalCollectie.getEmbedded().getOuders()
                    .stream()
                    .map(o -> o.getNaam())
                    .map(n -> n.getVoornamen())
                    .filter(v -> v.contains(value))
                    .findAny().orElse(null), is(notNullValue()));
        }
    }

    @Dan("heeft deze ouder {}")
    public void heeft_deze_ouder(final String field) {
        final String[] splittedField = field.split("=");
        final String key = splittedField[0];
        final String value = splittedField[1];
        switch (key) {
            case "burgerservicenummer":
                if (!value.equalsIgnoreCase("null")) {
                    assertThat(ouder.getBurgerservicenummer(), is(value));
                } else {
                    assertThat(ouder.getBurgerservicenummer(), is(String.valueOf(nullBsn)));
                }
                break;
            case "geslachtsaanduiding":
                assertThat(ouder.getGeslachtsaanduiding().name(), is(value));
                break;
            case "naam.voornamen":
                assertThat(ouder.getNaam(), is(notNullValue()));
                assertThat(ouder.getNaam().getVoornamen(), is(value));
                break;
            case "naam.voorvoegsel":
                assertThat(ouder.getNaam(), is(notNullValue()));
                if (ouder.getNaam().getVoorvoegsel() != null) {
                    assertThat(ouder.getNaam().getVoorvoegsel(), is(value));
                } else {
                    assertThat(ouderHal.getNaam().getVoorvoegsel(), is(nullValue()));
                }
                break;
            case "naam.geslachtsnaam":
                assertThat(ouder.getNaam(), is(notNullValue()));
                assertThat(ouder.getNaam().getGeslachtsnaam(), is(value));
                break;
            case "naam.adellijkeTitel_predikaat.omschrijvingAdellijkeTitel_predikaat":
                assertThat(ouder.getNaam(), is(notNullValue()));
                assertThat(ouder.getNaam().getAanschrijfwijze(), is(notNullValue()));
                if (!value.equals("null")) {
                    assertThat(ouder.getNaam().getAanschrijfwijze().toLowerCase().contains(value.toLowerCase()), is(true));
                } else {
                    assertThat(Arrays.stream(AdellijkeTitelPredikaat.values())
                            .map(AdellijkeTitelPredikaat::getOmschrijving)
                            .map(String::toLowerCase).anyMatch(str -> !ouder.getNaam().getAanschrijfwijze().contains(str)), is(true));
                }
                break;
            case "geboorte.datum.datum":
                assertThat(ouder.getGeboorte(), is(notNullValue()));
                assertThat(ouder.getGeboorte().getDatum().getDatum(), is(convertDatum(value)));
                break;
            case "geboorte.datum.dag":
                assertThat(ouder.getGeboorte(), is(notNullValue()));
                assertThat(ouder.getGeboorte().getDatum().getDag(), is(Integer.valueOf(value)));
                break;
            case "geboorte.datum.maand":
                assertThat(ouder.getGeboorte(), is(notNullValue()));
                assertThat(ouder.getGeboorte().getDatum().getMaand(), is(Integer.valueOf(value)));
                break;
            case "geboorte.plaats":
                assertThat(ouder.getGeboorte(), is(notNullValue()));
                assertThat(ouder.getGeboorte().getPlaats().getOmschrijving(), is(value));
                break;
            case "geboorte.land.landnaam":
                assertThat(ouder.getGeboorte(), is(notNullValue()));
                assertThat(ouder.getGeboorte().getLand().getOmschrijving(), is(value));
                break;
        }
    }

    @Gegeven("de Ouder{int} van de ingeschreven persoon heeft volgens categorie {int}\\/{int} naam {} {} {} {}, geboren in {} en geboortedatum {}")
    public void de_Ouder_van_de_ingeschreven_persoon_heeft_volgens_categorie_naam_Markiezin_Marie_du_Partenaire_geboren_in_Saintt_Quentin_en_Tourmont_en_geboortedatum_november(Integer ouderNum, Integer int2, Integer int3, String adellijkeTitel, String voornamen, String voorvoegsel, String geslachtsnaam, String geboortePlaats, String geboorteDatum) {
        final String landCode = "5002";
        final String adelijkeTitelString = AdellijkeTitelPredikaat.fromOmschrijving(adellijkeTitel).name();
        final String aanduidingAanschrijving = "E";
        insertInPersoon(voornamen, nullBsn, geslachtsnaam, voorvoegsel, aanduidingAanschrijving, adelijkeTitelString, geboortePlaats, landCode, GEBOORTE_DATUM);
        final Long ouderId = findIdFromGeslachtsnaamInPersoon(geslachtsnaam);
        insertInAfstamming(1L, ouderId, OuderAanduidingEnum.OUDER2.name(), INGANG_DATUM);
    }

    @Gegeven("de te raadplegen persoon heeft meerdere \\(twee) ouders \\(Marie en Cornelis Petrus Johannus)")
    public void de_te_raadplegen_persoon_heeft_meerdere_twee_ouders_Marie_en_Cornelis_Petrus_Johannus() {
        insertInPersoon("Marie", nullBsn, "Partenaire", "du", "E", "MI", "Saintt-Quentin-en-Tourmont", "5002", GEBOORTE_DATUM);
        insertInAfstamming(1L, findIdFromGeslachtsnaamInPersoon("Partenaire"), OuderAanduidingEnum.OUDER1.name(), INGANG_DATUM);
        insertInAfstamming(1L, findIdFromVoornamenInPersoon("Cornelis Petrus Johannus"), OuderAanduidingEnum.OUDER1.name(), INGANG_DATUM);
    }

    @Dan("heeft deze gevonden ouder de ingeschrevenpersonen link met \\/ingeschrevenpersonen\\/{int}")
    public void heeft_deze_gevonden_ouder_de_ingeschrevenpersonen_link_met_ingeschrevenpersonen(Integer int1) {

    }

    @Dan("heeft deze gevonden ouder een lege link ingeschrevenpersonen")
    public void heeft_deze_gevonden_ouder_een_lege_link_ingeschrevenpersonen() {
    }

    @Gegeven("de te raadplegen persoon heeft drie ouders")
    public void de_te_raadplegen_persoon_heeft_drie_ouders() {
        insertInPersoon("Marie", nullBsn, "Partenaire", "du", "E", "MI", "Saintt-Quentin-en-Tourmont", "5002", GEBOORTE_DATUM);
        insertInAfstamming(findIdFromBsnInPersoon(999999448), findIdFromGeslachtsnaamInPersoon("Partenaire"), OuderAanduidingEnum.OUDER1.name(), INGANG_DATUM);
        insertInAfstamming(findIdFromBsnInPersoon(999999448), findIdFromVoornamenInPersoon("Cornelis Petrus Johannus"), OuderAanduidingEnum.OUDER2.name(), INGANG_DATUM);
        insertInAfstamming(findIdFromBsnInPersoon(999999448), findIdFromBsnInPersoon(999993653), OuderAanduidingEnum.OUDER1.name(), INGANG_DATUM);
    }

    @Gegeven("de te raadplegen persoon heeft één ouder dat zelf ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_één_ouder_dat_zelf_ingeschreven_persoon_is() {

    }

    @Gegeven("de ouder van de ingeschreven persoon heeft in de registratie burgerservicenummer {int}, naam Çelik" + "\t" + "Groenen, geboren in januari {int} \\(geboortedag is onbekend)")
    public void de_ouder_van_de_ingeschreven_persoon_heeft_in_de_registratie_burgerservicenummer_naam_Çelik_Groenen_geboren_in_januari_geboortedag_is_onbekend(Integer int1, Integer int2) {
        insertInAfstamming(findIdFromBsnInPersoon(999999424), findIdFromBsnInPersoon(999999291), OuderAanduidingEnum.OUDER1.name(), INGANG_DATUM);
    }

    @Als("de link ouders wordt gevolgd")
    public void de_link_ouders_wordt_gevolgd() {
        ingeschrevenNatuurlijkPersoonOuders(world.persoonHal.getBurgerservicenummer());
    }

    @Als("de link ingeschrevenpersonen wordt gevolgd")
    public void de_link_ingeschrevenpersonen_wordt_gevolgd() throws URISyntaxException {
        if (world.ouderHalCollectie != null) {
            ingeschrevenNatuurlijkPersoonRestTemplateUri(world.ouderHalCollectie.getEmbedded().getOuders().stream().findFirst().get().getLinks().getIngeschrevenPersoon().getHref());
        }
        if (world.partnerHalCollectie != null) {
            ingeschrevenNatuurlijkPersoonRestTemplateUri(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get().getLinks().getIngeschrevenPersoon().getHref());
        }
        if (world.kindHalCollectie != null) {
            ingeschrevenNatuurlijkPersoonRestTemplateUri(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getLinks().getIngeschrevenPersoon().getHref());
        }
    }

}

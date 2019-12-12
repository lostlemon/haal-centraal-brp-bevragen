package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHalCollectie;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.KindHal;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.OuderHal;
import org.hamcrest.CoreMatchers;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@Slf4j
public class StepDefinitions extends Defaults {

    private ResponseEntity<IngeschrevenPersoonHalCollectie> persoonCollectie;

    @Gegeven("op de PL van een ingeschreven persoon is categorie {int}, element {double} gelijk aan {int} \\(= persoon is uitgesloten)")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_element_gelijk_aan_persoon_is_uitgesloten(Integer int1, Double double1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("categorie {int}, element {double} is niet aanwezig of heeft geen waarde")
    public void categorie_element_is_niet_aanwezig_of_heeft_geen_waarde(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is europeesKiesrecht gelijk aan false")
    public void is_europeesKiesrecht_gelijk_aan_false() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("op de PL van een ingeschreven persoon is categorie {int}, element {double} gelijk aan {int} \\(= persoon ontvangt oproep)")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_element_gelijk_aan_persoon_ontvangt_oproep(Integer int1, Double double1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is europeesKiesrecht gelijk aan true")
    public void is_europeesKiesrecht_gelijk_aan_true() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("op de PL van een ingeschreven persoon is categorie {int}, element {double} is leeg")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_element_is_leeg(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is europeesKiesrecht niet aanwezig")
    public void is_europeesKiesrecht_niet_aanwezig() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("categorie {int}, element {double} is een datum in het verleden")
    public void categorie_element_is_een_datum_in_het_verleden(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is einddatumUitsluitingEuropeesKiesrecht niet aanwezig")
    public void is_einddatumUitsluitingEuropeesKiesrecht_niet_aanwezig() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("categorie {int}, element {double} is een datum in de toekomst")
    public void categorie_element_is_een_datum_in_de_toekomst(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is einddatumUitsluitingEuropeesKiesrecht gelijk aan de waarde van element {double}")
    public void is_einddatumUitsluitingEuropeesKiesrecht_gelijk_aan_de_waarde_van_element(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("op de PL van een ingeschreven persoon is categorie {int}, element {double} gelijk aan {int}")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_element_gelijk_aan(Integer int1, Double double1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de ingeschreven persoon wordt geraadpleegd op {int} september {int}")
    public void de_ingeschreven_persoon_wordt_geraadpleegd_op_september(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is europeesKiesrecht false")
    public void is_europeesKiesrecht_false() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is einddatumUitsluitingEuropeesKiesrecht jaar: {int}")
    public void is_einddatumUitsluitingEuropeesKiesrecht_jaar(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is einddatumUitsluitingEuropeesKiesrecht jaar: {int}, maand: {int}")
    public void is_einddatumUitsluitingEuropeesKiesrecht_jaar_maand(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("op de PL van een ingeschreven persoon is categorie {int}, element {double} gelijk aan A \\(= persoon is uitgesloten)")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_element_gelijk_aan_A_persoon_is_uitgesloten(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is uitgeslotenVanKiesrecht gelijk aan true")
    public void is_uitgeslotenVanKiesrecht_gelijk_aan_true() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("op de PL van een ingeschreven persoon is er geen categorie {int}, element {double} \\(= persoon is niet uitgesloten)")
    public void op_de_PL_van_een_ingeschreven_persoon_is_er_geen_categorie_element_persoon_is_niet_uitgesloten(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is uitgeslotenVanKiesrecht niet aanwezig")
    public void is_uitgeslotenVanKiesrecht_niet_aanwezig() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is einddatumUitsluitingKiesrecht niet aanwezig")
    public void is_einddatumUitsluitingKiesrecht_niet_aanwezig() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is einddatumUitsluitingKiesrecht gelijk aan de waarde van element {double}")
    public void is_einddatumUitsluitingKiesrecht_gelijk_aan_de_waarde_van_element(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is uitgeslotenVanKiesrecht <europeesKiesrecht>")
    public void is_uitgeslotenVanKiesrecht_europeesKiesrecht() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon de heer F.C. Groen is getrouwd in {int} met Geel")
    public void de_ingeschreven_persoon_de_heer_F_C_Groen_is_getrouwd_in_met_Geel(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon is getrouwd in {int} met Roodt")
    public void de_ingeschreven_persoon_is_getrouwd_in_met_Roodt(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("geen van beide relaties is beëindigd")
    public void geen_van_beide_relaties_is_beëindigd() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon heeft aanduidingAanschrijving={string}")
    public void de_ingeschreven_persoon_heeft_aanduidingAanschrijving(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon de heer J. Wit is getrouwd in {int} met Geel")
    public void de_ingeschreven_persoon_de_heer_J_Wit_is_getrouwd_in_met_Geel(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("het huwelijk met Geel is ontbonden in {int}")
    public void het_huwelijk_met_Geel_is_ontbonden_in(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("het huwelijk met Roodt is ontbonden in {int}")
    public void het_huwelijk_met_Roodt_is_ontbonden_in(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon de heer J. Wit is getrouwd in {int} met Zwart")
    public void de_ingeschreven_persoon_de_heer_J_Wit_is_getrouwd_in_met_Zwart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon is getrouwd in {int} met Blaauw")
    public void de_ingeschreven_persoon_is_getrouwd_in_met_Blaauw(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("het huwelijk met Blaauw is ontbonden in {int}")
    public void het_huwelijk_met_Blaauw_is_ontbonden_in(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("het huwelijk met Zwart is ontbonden in {int}")
    public void het_huwelijk_met_Zwart_is_ontbonden_in(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon F.C. Groen is getrouwd in {int} met Geel")
    public void de_ingeschreven_persoon_F_C_Groen_is_getrouwd_in_met_Geel(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ingeschreven persoon J. Wit is getrouwd in {int} met Geel")
    public void de_ingeschreven_persoon_J_Wit_is_getrouwd_in_met_Geel(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon een actuele partner\\(partnerschap of huwelijk), ouders en kinderen heeft")
    public void de_te_raadplegen_persoon_een_actuele_partner_partnerschap_of_huwelijk_ouders_en_kinderen_heeft() {
        // aangenomen dat dit in de testdata zit
    }

    @Gegeven("in onderstaande scenario's wordt de fields-parameter niet gebruikt, tenzij expliciet aangegeven")
    public void in_onderstaande_scenario_s_wordt_de_fields_parameter_niet_gebruikt_tenzij_expliciet_aangegeven() {
        // aangenomen dat dit in de testdata zit
    }

    @Gegeven("de gebruiker geautoriseerd is voor gevraagde gegevens \\(sub-resources), tenzij expliciet anders aangegeven")
    public void de_gebruiker_geautoriseerd_is_voor_gevraagde_gegevens_sub_resources_tenzij_expliciet_anders_aangegeven() {
        // aangenomen dat dit in de testdata zit
    }

    @Als("een ingeschreven persoon wordt geraadpleegd zonder expand-parameter")
    public void een_ingeschreven_persoon_wordt_geraadpleegd_zonder_expand_parameter() {
        ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, null, null);
    }

    @Dan("wordt voor alle ouders in _embedded attribuut {word} teruggegeven")
    public void wordt_voor_alle_ouders_in__embedded_attribuut_x_teruggegeven(String attribute) {

        final Map<String, Function<OuderHal, ?>> map = new HashMap<>();

        map.put("geslachtsaanduiding", OuderHal::getGeslachtsaanduiding);
        map.put("ouder_aanduiding", OuderHal::getOuderAanduiding);
        map.put("_links.self", o -> o.getLinks().getSelf());

        if (!map.keySet().contains(attribute)) {
            throw new IllegalArgumentException("Onkened attribute: " + attribute);
        }

        world.persoonHal
                .getEmbedded()
                .getOuders()
                .stream()
                .forEach(o -> assertThat(attribute + " is null for bsn " + o.getBurgerservicenummer(), map.get(attribute).apply(o), is(not(nullValue()))));
        ;
    }

    @Dan("is voor alle ouders in _embedded attribuut {word} niet aanwezig")
    public void is_voor_alle_ouders_in__embedded_attribuut_x_niet_aanwezig(String attribute) {

        final Map<String, Function<OuderHal, ?>> map = new HashMap<>();

        map.put("burgerservicenummer", OuderHal::getBurgerservicenummer);
        map.put("naam", OuderHal::getNaam);
        map.put("geboorte", OuderHal::getGeboorte);
        map.put("_links.ingeschrevenpersonen", o -> o.getLinks().getIngeschrevenPersoon());
        map.put("geldigVan", (p) -> null);
        map.put("geldigTotEnMet", (p) -> null);

        if (!map.keySet().contains(attribute)) {
            throw new IllegalArgumentException("Onbekend attribute: " + attribute);
        }

        world.persoonHal
                .getEmbedded()
                .getOuders()
                .stream()
                .forEach(o -> assertThat(attribute + " is not null for bsn" + o.getBurgerservicenummer(), map.get(attribute).apply(o), is(nullValue())));
        ;
    }

    @Dan("worden voor alle kinderen in _embedded alle attributen van naam teruggegeven voor zover ze een waarde hebben \\(voornamen, geslachtsnaam)")
    public void worden_voor_alle_kinderen_in__embedded_alle_attributen_van_naam_teruggegeven_voor_zover_ze_een_waarde_hebben_voornamen_geslachtsnaam() {
        world.persoonHal.getEmbedded().getKinderen().stream()
                .forEach(k -> {
                    assertThat(k.getNaam(), is(not(nullValue())));
                    assertThat(k.getNaam().getGeslachtsnaam(), is(not(nullValue())));
                    assertThat(k.getNaam().getVoorletters(), is(not(nullValue())));
                    assertThat(k.getNaam().getVoornamen(), is(not(nullValue())));
                    //assertThat(k.getNaam().getVoorvoegsel(), is(not(nullValue())));
                });
    }

    @Dan("worden voor alle kinderen in _embedded alle attributen van geboorte teruggegeven voor zover ze een waarde hebben \\(plaats, datum, land)")
    public void worden_voor_alle_kinderen_in__embedded_alle_attributen_van_geboorte_teruggegeven_voor_zover_ze_een_waarde_hebben_plaats_datum_land() {
        world.persoonHal.getEmbedded().getKinderen().stream()
                .forEach(k -> {
                    assertThat(k.getGeboorte(), is(not(nullValue())));
                    assertThat(k.getGeboorte().getDatum(), is(not(nullValue())));
//            assertThat(k.getGeboorte().getInOnderzoek(), is(not(nullValue()))); // not implemented
                    assertThat(k.getGeboorte().getLand(), is(not(nullValue())));
                    assertThat(k.getGeboorte().getLand(), is(not(nullValue())));
                });
    }

    @Dan("wordt voor alle kinderen in _embedded attribuut _links.self teruggegeven")
    public void wordt_voor_alle_kinderen_in__embedded_attribuut__links_self_teruggegeven() {
        world.persoonHal.getEmbedded().getKinderen().stream()
                .forEach(k -> {
                    assertThat(k.getLinks().getSelf(), is(not(nullValue())));
                });
    }

    @Dan("is voor alle kinderen in _embedded attribuut {word} niet aanwezig")
    public void is_voor_alle_kinderen_in__embedded_attribuut_x_niet_aanwezig(final String attribute) {
        final Map<String, Function<KindHal, ?>> map = new HashMap<>();

        map.put("burgerservicenummer", KindHal::getBurgerservicenummer);
        map.put("geldigVan", p -> null); // zit niet in KindHal
        map.put("geldigTotEnMet", p -> null);  // zit niet in KindHal
        map.put("_links.ingeschrevenpersonen", o -> o.getLinks().getIngeschrevenPersoon());

        if (!map.keySet().contains(attribute)) {
            throw new IllegalArgumentException("Onbekend attribute: " + attribute);
        }

        world.persoonHal
                .getEmbedded()
                .getKinderen()
                .stream()
                .forEach(o -> assertThat(attribute + " is not null for bsn" + o.getBurgerservicenummer(), map.get(attribute).apply(o), is(nullValue())));
        ;
    }

    @Dan("wordt voor alle kinderen in _embedded attribuut naam.voornamen teruggegeven")
    public void wordt_voor_alle_kinderen_in__embedded_attribuut_naam_voornamen_teruggegeven() {
        world.persoonHal.getEmbedded().getKinderen() //
                .stream().forEach(k -> {
            assertThat(k.getNaam().getVoornamen(), is(not(nullValue())));
        });
    }

    @Dan("wordt voor alle kinderen in _embedded attribuut naam.geslachtsnaam teruggegeven")
    public void wordt_voor_alle_kinderen_in__embedded_attribuut_naam_geslachtsnaam_teruggegeven() {
        world.persoonHal.getEmbedded().getKinderen() //
                .stream().forEach(k -> {
            assertThat(k.getNaam().getGeslachtsnaam(), is(not(nullValue())));
        });
    }

    @Dan("wordt voor alle kinderen in _embedded geen enkel ander attribuut dan naam en _links teruggegeven")
    public void wordt_voor_alle_kinderen_in__embedded_geen_enkel_ander_attribuut_dan_naam_en__links_teruggegeven() {
        world.persoonHal.getEmbedded().getKinderen() //
                .stream().forEach(k -> {
            assertThat(k.getBurgerservicenummer(), is(nullValue()));
            assertThat(k.getNaam(), is(not(nullValue())));
            assertThat(k.getLinks(), is(not(nullValue())));
            assertThat(k.getLinks().getSelf(), is(not(nullValue())));
            assertThat(k.getLinks().getIngeschrevenPersoon(), is(nullValue()));
            assertThat(k.getGeboorte(), is(nullValue()));
        });
    }

    @Dan("wordt voor alle kinderen in _embedded geen enkel ander attribuut van naam teruggegeven dan voornamen en geslachtsnaam")
    public void wordt_voor_alle_kinderen_in__embedded_geen_enkel_ander_attribuut_van_naam_teruggegeven_dan_voornamen_en_geslachtsnaam() {
        world.persoonHal.getEmbedded().getKinderen() //
                .stream().forEach(k -> {
            assertThat(k.getBurgerservicenummer(), is(nullValue()));
            assertThat(k.getNaam().getVoornamen(), is(not(nullValue())));
            assertThat(k.getNaam().getGeslachtsnaam(), is(not(nullValue())));
            assertThat(k.getGeboorte(), is(nullValue()));
            assertThat(k.getLinks().getSelf(), is(not(nullValue())));
            assertThat(k.getLinks().getIngeschrevenPersoon(), is(nullValue()));
        });
    }

    @Dan("wordt voor alle kinderen in _embedded geen enkel ander attribuut van _links teruggegeven dan self")
    public void wordt_voor_alle_kinderen_in__embedded_geen_enkel_ander_attribuut_van__links_teruggegeven_dan_self() {
        world.persoonHal.getEmbedded().getKinderen() //
                .stream().forEach(k -> {
            assertThat(k.getLinks().getSelf(), is(not(nullValue())));
            assertThat(k.getLinks().getIngeschrevenPersoon(), is(nullValue()));
        });
    }

    @Dan("wordt voor alle kinderen in _embedded attribuut _links.ingeschrevenpersonen teruggegeven")
    public void wordt_voor_alle_kinderen_in__embedded_attribuut__links_ingeschrevenpersonen_teruggegeven() {
        world.persoonHal.getEmbedded().getKinderen() //
                .stream().forEach(k -> {
            assertThat(k.getLinks(), is(not(nullValue())));
            assertThat(k.getLinks().getSelf(), is(not(nullValue())));
            assertThat(k.getLinks().getIngeschrevenPersoon(), is(not(nullValue())));
        });
    }

    @Gegeven("de gebruiker is geautoriseerd voor geboortedatum")
    public void de_gebruiker_is_geautoriseerd_voor_geboortedatum() {
        throw new cucumber.api.PendingException("Out of scope");
    }

    @Gegeven("de gebruiker is niet geautoriseerd voor geboorteplaats")
    public void de_gebruiker_is_niet_geautoriseerd_voor_geboorteplaats() {
        throw new cucumber.api.PendingException("Out of scope");
    }

    @Gegeven("de te raadplegen persoon verblijft in het buitenland")
    public void de_te_raadplegen_persoon_verblijft_in_het_buitenland() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft geboortedatum in onderzoek \\({double} = {int})")
    public void de_te_raadplegen_persoon_heeft_geboortedatum_in_onderzoek(Double double1, Integer int1) {
        // zit in testset
    }

    @Gegeven("de te raadplegen persoon heeft naam in onderzoek \\({double} = {int})")
    public void de_te_raadplegen_persoon_heeft_naam_in_onderzoek(Double double1, Integer int1) {
        // zit in testset
    }


    @Gegeven("de te raadplegen persoon heeft de hele categorie persoon in onderzoek \\({double} = {int})")
    public void de_te_raadplegen_persoon_heeft_de_hele_categorie_persoon_in_onderzoek(Double double1, Integer int1) {
        // zit in testset
    }

    @Gegeven("de te raadplegen persoon heeft indicatie geheim {string} \\({double} = {int})")
    public void de_te_raadplegen_persoon_heeft_indicatie_geheim(String string, Double double1, Integer int1) {
        // zit in testset
    }

    @Als("ingeschrevenpersonen worden gezocht met {word}={word}")
    public void de_worden_geraadpleegd_met(String parameter, String waarde) throws URISyntaxException {
        callRestTemplate("?burgerservicenummer=999993677&" + parameter + "=" + waarde);
    }

    @Als("de ingeschrevenpersonen wordt geraadpleegd met {word}={word}")
    public void de_wordt_geraadpleegd_met(String parameter, String waarde) throws URISyntaxException {
        switch (parameter) {
            case "burgerservicenummer": {
                ingeschrevenNatuurlijkPersoon(waarde, null, null);
                break;
            }
            case "fields": {
                ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, null, waarde);
                break;
            }
            case "expand": {
                ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, waarde, null);
                break;
            }
            default: {
                ingeschrevenNatuurlijkPersoonRestTemplate(DEFAULT_BSN, parameter + "=" + waarde);
                break;
            }
        }

    }


    @Dan("is de http status code van het antwoord {int}")
    public void is_de_http_status_code_van_het_antwoord(Integer int1) {
        assertThat(world.statusCode, is(int1));
    }

    @Dan("eindigt attribuut instance met {}")
    public void eindigt_attribuut_instance_met_api_handelsregister_v(String param) {
    }

    @Dan("bevat invalid-params exact {int} voorkomen\\(s)")
    public void bevat_invalid_params_exact_voorkomen_s(Integer int1) {
        assertThat(world.validatieFoutbericht.getInvalidParams(), hasSize(int1));
    }

    @Als("\\{resource} worden gezocht met \\{parameter}=\\{waarde}")
    public void worden_gezocht_met() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("ingeschrevenpersonen worden gezocht zonder parameters")
    public void ingeschrevenpersonen_worden_gezocht_zonder_parameters() throws URISyntaxException {
        callRestTemplate("");
    }

    @Dan("komt attribuut invalid-params niet voor in het antwoord")
    public void komt_attribuut_invalid_params_niet_voor_in_het_antwoord() {
        assertThat(world.validatieFoutbericht.getInvalidParams(), hasSize(0));
    }

    @En("is er een invalid-params met name={}")
    public void is_er_een_invalid_params_met_name_verblijfplaats__huisnummer(String attributeName) {
        assertThat(world.validatieFoutbericht.getInvalidParams().stream().anyMatch(p -> p.getName().equals(attributeName)), is(true));
    }

    @Als("ingeschrevenpersonen worden gezocht zonder authenticatiegegevens \\(zonder SAML assertion)")
    public void ingeschrevenpersonen_worden_gezocht_zonder_authenticatiegegevens_zonder_SAML_assertion() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("ingeschrevenpersonen worden gezocht met invalide authenticatiegegevens \\(onjuiste SAML assertion)")
    public void ingeschrevenpersonen_worden_gezocht_met_invalide_authenticatiegegevens_onjuiste_SAML_assertion() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("ingeschrevenpersonen worden gezocht met onbekende gebruiker \\(onbekende SAML assertion)")
    public void ingeschrevenpersonen_worden_gezocht_met_onbekende_gebruiker_onbekende_SAML_assertion() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("ingeschrevenpersonen worden gezocht met een geauthentiseerde gebruiker zonder rechten op de API")
    public void ingeschrevenpersonen_worden_gezocht_met_een_geauthentiseerde_gebruiker_zonder_rechten_op_de_API() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de ingeschrevenpersonen wordt geraadpleegd met acceptheader application\\/xml")
    public void de_ingeschrevenpersonen_wordt_geraadpleegd_met_acceptheader_application_xml() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("een ingeschreven persoon wordt geraadpleegd")
    public void een_ingeschreven_persoon_wordt_geraadpleegd() {
        ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, null, null);
    }

    @Als("de bron GBA-V geen response of een timeout geeft")
    public void de_bron_GBA_V_geen_response_of_een_timeout_geeft() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de bron GBA-V geeft de foutmelding “Service is niet geactiveerd voor dit account.”")
    public void de_bron_GBA_V_geeft_de_foutmelding_Service_is_niet_geactiveerd_voor_dit_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer In het Veld")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_In_het_Veld() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer Groenen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_Groenen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Van Velzen-in het Veld")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Van_Velzen_in_het_Veld() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Groenen-Groenink")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Groenen_Groenink() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Van Velzen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Van_Velzen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Groenen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Groenen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer In het Veld-van Velzen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_In_het_Veld_van_Velzen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer Groenen-Groenink")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_Groenen_Groenink() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=baron Van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_baron_Van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=barones Van den Aedel-van der Veen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_barones_Van_den_Aedel_van_der_Veen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer Van der Veen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_Van_der_Veen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Van der Veen-gravin van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Van_der_Veen_gravin_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=prins Van Roodt de Wit Blaauw")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_prins_Van_Roodt_de_Wit_Blaauw() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=prinses Van Roodt de Wit Blaauw")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_prinses_Van_Roodt_de_Wit_Blaauw() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=ridder Van Hoogh")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_ridder_Van_Hoogh() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=<gebruikInLopendeTekst>")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_gebruikInLopendeTekst() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Van der Veen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Van_der_Veen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Van der Veen-barones van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Van_der_Veen_barones_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=barones Van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_barones_Van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer Van der Veen-graaf van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_Van_der_Veen_graaf_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=graaf Van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_graaf_Van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=graaf Van den Aedel-van der Veen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_graaf_Van_den_Aedel_van_der_Veen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=de heer Van der Veen-van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_de_heer_Van_der_Veen_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw Van der Veen-van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_Van_der_Veen_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw van der Veen-van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_van_der_Veen_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw van den Aedel")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_van_den_Aedel() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.gebruikInLopendeTekst=mevrouw van den Aedel-van der Veen")
    public void heeft_de_gevonden_ingeschrevenpersoon_naam_gebruikInLopendeTekst_mevrouw_van_den_Aedel_van_der_Veen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }


    @Gegeven("de partner heeft een geslachtswijziging ondergaan")
    public void de_partner_heeft_een_geslachtswijziging_ondergaan() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de persoon heeft een verzoek ingediend dat het geslachtswijziging van de partner op de eigen persoonslijst ongedaan wordt gemaakt")
    public void de_persoon_heeft_een_verzoek_ingediend_dat_het_geslachtswijziging_van_de_partner_op_de_eigen_persoonslijst_ongedaan_wordt_gemaakt() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft deze partner de geslachtsaanduiding voorafgaand aan de geslachtswijziging.")
    public void heeft_deze_partner_de_geslachtsaanduiding_voorafgaand_aan_de_geslachtswijziging() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ex-partner heeft een geslachtswijziging ondergaan")
    public void de_ex_partner_heeft_een_geslachtswijziging_ondergaan() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de persoon heeft een verzoek ingediend dat het geslachtswijziging van de ex-partner op de eigen persoonslijst ongedaan wordt gemaakt")
    public void de_persoon_heeft_een_verzoek_ingediend_dat_het_geslachtswijziging_van_de_ex_partner_op_de_eigen_persoonslijst_ongedaan_wordt_gemaakt() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt de ex-partner gevonden met burgerservicenummer={int}")
    public void wordt_de_ex_partner_gevonden_met_burgerservicenummer(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft deze ex-partner de geslachtsaanduiding voorafgaand aan de geslachtswijziging.")
    public void heeft_deze_ex_partner_de_geslachtsaanduiding_voorafgaand_aan_de_geslachtswijziging() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }


    @Gegeven("het kind heeft een geslachtswijziging ondergaan")
    public void het_kind_heeft_een_geslachtswijziging_ondergaan() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de persoon heeft een verzoek ingediend dat het geslachtswijziging van het kind op de eigen persoonslijst ongedaan wordt gemaakt")
    public void de_persoon_heeft_een_verzoek_ingediend_dat_het_geslachtswijziging_van_het_kind_op_de_eigen_persoonslijst_ongedaan_wordt_gemaakt() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft dit kind de geslachtsaanduiding voorafgaand aan de geslachtswijziging.")
    public void heeft_dit_kind_de_geslachtsaanduiding_voorafgaand_aan_de_geslachtswijziging() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ouder van de ingeschreven persoon heeft in de registratie burgerservicenummer {int},")
    public void de_ouder_van_de_ingeschreven_persoon_heeft_in_de_registratie_burgerservicenummer(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de ouder heeft een geslachtswijziging ondergaan")
    public void de_ouder_heeft_een_geslachtswijziging_ondergaan() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de persoon heeft een verzoek ingediend dat het geslachtswijziging van de ouder op de eigen persoonslijst ongedaan wordt gemaakt")
    public void de_persoon_heeft_een_verzoek_ingediend_dat_het_geslachtswijziging_van_de_ouder_op_de_eigen_persoonslijst_ongedaan_wordt_gemaakt() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft deze ouder de geslachtsaanduiding voorafgaand aan de geslachtswijziging.")
    public void heeft_deze_ouder_de_geslachtsaanduiding_voorafgaand_aan_de_geslachtswijziging() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de API {string} is beschikbaar")
    public void de_API_is_beschikbaar(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft een actuele partner")
    public void de_te_raadplegen_persoon_heeft_een_actuele_partner() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("ingeschreven persoon met burgerservicenummer {int} wordt geraadpleegd zonder fields-parameter")
    public void ingeschreven_persoon_met_burgerservicenummer_wordt_geraadpleegd_zonder_fields_parameter(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("bevat het antwoord _links.partnerhistorie.href met een waarde")
    public void bevat_het_antwoord__links_partnerhistorie_href_met_een_waarde() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("bevat het antwoord _links.verblijfplaatsenhistorie.href met een waarde")
    public void bevat_het_antwoord__links_verblijfplaatsenhistorie_href_met_een_waarde() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("bevat het antwoord _links.verblijfstitelshistorie.href met een waarde")
    public void bevat_het_antwoord__links_verblijfstitelshistorie_href_met_een_waarde() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("eindigt attribuut _links.partnerhistorie.href met \\/api\\/bevragingen_ingeschreven_personen\\/v{int}\\/historie\\/ingeschrevenpersonen\\/{int}\\/partners")
    public void eindigt_attribuut__links_partnerhistorie_href_met_api_bevragingen_ingeschreven_personen_v_historie_ingeschrevenpersonen_partners(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("eindigt attribuut _links.verblijfplaatsenhistorie.href met \\/api\\/bevragingen_ingeschreven_personen\\/v{int}\\/historie\\/ingeschrevenpersonen\\/{int}\\/verblijfplaatsen")
    public void eindigt_attribuut__links_verblijfplaatsenhistorie_href_met_api_bevragingen_ingeschreven_personen_v_historie_ingeschrevenpersonen_verblijfplaatsen(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("eindigt attribuut _links.verblijfstitelshistorie.href met \\/api\\/bevragingen_ingeschreven_personen\\/v{int}\\/historie\\/ingeschrevenpersonen\\/{int}\\/verblijfstitels")
    public void eindigt_attribuut__links_verblijfstitelshistorie_href_met_api_bevragingen_ingeschreven_personen_v_historie_ingeschrevenpersonen_verblijfstitels(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de API {string} is niet beschikbaar")
    public void de_API_is_niet_beschikbaar(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft elke gevonden persoon attribuut {} met een waarde")
    public void heeft_elke_gevonden_persoon_attribuut__links_partnerhistorie_href_met_een_waarde() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("deze ingeschreven persoon staat vanaf {int}{int}{int} ingeschreven met de volgende gegevens:")
    public void deze_ingeschreven_persoon_staat_vanaf_ingeschreven_met_de_volgende_gegevens(Integer int1, Integer int2, Integer int3, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }

    @Gegeven("een ingeschreven persoon kent de volgende historie in de registratie:")
    public void een_ingeschreven_persoon_kent_de_volgende_historie_in_de_registratie(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }

    @Als("de partnerhistorie van ingeschreven persoon {int} wordt geraadpleegd vanaf {int}{int}{int}")
    public void de_partnerhistorie_van_ingeschreven_persoon_wordt_geraadpleegd_vanaf(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("worden er {int} partners teruggegeven")
    public void worden_er_partners_teruggegeven(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e partner burgerservicenummer {int} en geldigVan {int}{int}{int}")
    public void heeft_de_e_partner_burgerservicenummer_en_geldigVan(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e partner burgerservicenummer {int} en geldigTotEnMet {int}{int}{int}")
    public void heeft_de_e_partner_burgerservicenummer_en_geldigTotEnMet(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de verblijfplaatshistorie van ingeschreven persoon {int} wordt geraadpleegd vanaf {int}{int}{int}")
    public void de_verblijfplaatshistorie_van_ingeschreven_persoon_wordt_geraadpleegd_vanaf(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("worden er {int} verblijfplaatsen teruggegeven")
    public void worden_er_verblijfplaatsen_teruggegeven(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e verblijfplaats naamOpenbareRuimte Loosduinsekade en geldigVan {int}{int}{int}")
    public void heeft_de_e_verblijfplaats_naamOpenbareRuimte_Loosduinsekade_en_geldigVan(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e verblijfplaats naamOpenbareRuimte Kerkstraat en geldigVan {int}{int}{int}")
    public void heeft_de_e_verblijfplaats_naamOpenbareRuimte_Kerkstraat_en_geldigVan(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e verblijfplaats naamOpenbareRuimte Voorstraat en geldigVan {int}{int}{int}")
    public void heeft_de_e_verblijfplaats_naamOpenbareRuimte_Voorstraat_en_geldigVan(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("worden er {int} verblijfstitels teruggegeven")
    public void worden_er_verblijfstitels_teruggegeven(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e verblijfstitel verblijfstitelNumeriek {int} en geldigVan {int}{int}{int}")
    public void heeft_de_e_verblijfstitel_verblijfstitelNumeriek_en_geldigVan(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de volgende personen hebben postcode {int}AK en huisnummer {int} als verblijfplaats:")
    public void de_volgende_personen_hebben_postcode_AK_en_huisnummer_als_verblijfplaats(Integer int1, Integer int2, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }

    @Als("de bewoningen wordt gezocht op postcode {int}AK en huisnummer {int} vanaf {int}{int}{int}")
    public void de_bewoningen_wordt_gezocht_op_postcode_AK_en_huisnummer_vanaf(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("worden er {int} bewoners teruggegeven")
    public void worden_er_bewoners_teruggegeven(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e bewoner burgerservicenummer {int} en geldigVan {int}{int}{int} en geldigTotEnMet is null of niet opgenomen")
    public void heeft_de_e_bewoner_burgerservicenummer_en_geldigVan_en_geldigTotEnMet_is_null_of_niet_opgenomen(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft de {int}e bewoner burgerservicenummer {int} en geldigVan {int}{int}{int} en geldigTotEnMet {int}{int}{int}")
    public void heeft_de_e_bewoner_burgerservicenummer_en_geldigVan_en_geldigTotEnMet(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5, Integer int6, Integer int7, Integer int8) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft de hele persoon in onderzoek \\({double}{double}={int})")
    public void de_te_raadplegen_persoon_heeft_de_hele_persoon_in_onderzoek(Double double1, Double double2, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("geen enkele andere categorie, groep of attribuut is in onderzoek")
    public void geen_enkele_andere_categorie_groep_of_attribuut_is_in_onderzoek() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft categorie nationaliteit in onderzoek")
    public void de_te_raadplegen_persoon_heeft_categorie_nationaliteit_in_onderzoek() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft groep naam in onderzoek")
    public void de_te_raadplegen_persoon_heeft_groep_naam_in_onderzoek() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft attribuut naam.voornamen in onderzoek")
    public void de_te_raadplegen_persoon_heeft_attribuut_naam_voornamen_in_onderzoek() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft in onderzoek gevuld \\({int}), met datum einde in onderzoek ook gevuld")
    public void de_te_raadplegen_persoon_heeft_in_onderzoek_gevuld_met_datum_einde_in_onderzoek_ook_gevuld(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft in onderzoek gevuld op attribuut \\(048510)")
    public void de_te_raadplegen_persoon_heeft_in_onderzoek_gevuld_op_attribuut() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de persoon wordt geraadpleegd met burgerservicenummer {int}")
    public void de_persoon_wordt_geraadpleegd_met_burgerservicenummer(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt de gegevensgroep overlijden niet getoond.")
    public void wordt_de_gegevensgroep_overlijden_niet_getoond() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt de gegevensgroep verblijfsplaats inclusief indicatie onjuist getoond.")
    public void wordt_de_gegevensgroep_verblijfsplaats_inclusief_indicatie_onjuist_getoond() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("geldt voor elk van de gevonden ingeschrevenpersonen dat attribuut _links.self.href begint met http:\\/\\/ of https:\\/\\/")
    public void geldt_voor_elk_van_de_gevonden_ingeschrevenpersonen_dat_attribuut__links_self_href_begint_met_http_of_https() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("geldt voor elk van de gevonden ingeschrevenpersonen dat attribuut _links.self.href geen waarde fields bevat")
    public void geldt_voor_elk_van_de_gevonden_ingeschrevenpersonen_dat_attribuut__links_self_href_geen_waarde_fields_bevat() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("geldt voor elk van de gevonden ingeschrevenpersonen dat attribuut _links.self.href geen waarde expand bevat")
    public void geldt_voor_elk_van_de_gevonden_ingeschrevenpersonen_dat_attribuut__links_self_href_geen_waarde_expand_bevat() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("levert voor elk van de gevonden ingeschrevenpersonen een GET request naar de url in _links.self.href een antwoord")
    public void levert_voor_elk_van_de_gevonden_ingeschrevenpersonen_een_GET_request_naar_de_url_in__links_self_href_een_antwoord() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("geldt voor elk van de gevonden ingeschrevenpersonen dat attribuut _links.self.href de tekst {} bevat")
    public void geldt_voor_elk_van_de_gevonden_ingeschrevenpersonen_dat_attribuut__links_self_href_de_tekst_bevat(String parameter) {
        assertThat(world.persoonCollectie.getEmbedded(), is(not(nullValue())));
        world.persoonCollectie.getEmbedded().getIngeschrevenpersonen().stream().forEach(i -> {
            assertThat(i.getLinks().getSelf(), is(not(nullValue())));
            assertThat(i.getLinks().getSelf().getHref().toString(), CoreMatchers.containsString(parameter));
        });
    }

    @Als("ingeschreven persoon met burgerservicenummer {int} wordt geraadpleegd zonder fields-parameter en zonder expand-parameter")
    public void ingeschreven_persoon_met_burgerservicenummer_wordt_geraadpleegd_zonder_fields_parameter_en_zonder_expand_parameter(Integer int1) {
        ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, null, null);
    }

    @Als("ingeschreven persoon met burgerservicenummer {word} wordt geraadpleegd met {}")
    public void ingeschreven_persoon_met_burgerservicenummer_wordt_geraadpleegd_met_fields_naam_geboorte_overlijden_expand_partners_kinderen(String bsn, String query) throws URISyntaxException {
        ingeschrevenNatuurlijkPersoonRestTemplate(bsn, query);
    }

    @Gegeven("nummeraanduidingen zijn te raadplegen in een BAG API")
    public void nummeraanduidingen_zijn_te_raadplegen_in_een_BAG_API() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de geraadpleegde ingeschreven persoon heeft een BAG adres \\(nummeraanduiding) als verblijfplaats")
    public void de_geraadpleegde_ingeschreven_persoon_heeft_een_BAG_adres_nummeraanduiding_als_verblijfplaats() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("bevat attribuut _links.nummeraanduidingen.href \\/api\\/")
    public void bevat_attribuut__links_nummeraanduidingen_href_api() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("begint attribuut _links.nummeraanduidingen.href met http:\\/\\/ of https:\\/\\/")
    public void begint_attribuut__links_nummeraanduidingen_href_met_http_of_https() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("zit er geen ? in _links.nummeraanduidingen.href")
    public void zit_er_geen_vraagteken_in__links_nummeraanduidingen_href() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("zit er geen & in _links.nummeraanduidingen.href")
    public void zit_er_geen_amperdand_in__links_nummeraanduidingen_href() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("eindigt attribuut _links.nummeraanduidingen.href niet op /")
    public void eindigt_attribuut__links_nummeraanduidingen_href_niet_op() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("zit er geen bevragingen_ingeschreven_personen in _links.nummeraanduidingen.href")
    public void zit_er_geen_bevragingen_ingeschreven_personen_in__links_nummeraanduidingen_href() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("zit er geen ingeschrevenpersonen in _links.nummeraanduidingen.href")
    public void zit_er_geen_ingeschrevenpersonen_in__links_nummeraanduidingen_href() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("zit er geen {int} in _links.nummeraanduidingen.href")
    public void zit_er_geen_in__links_nummeraanduidingen_href(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("levert een GET request naar de url in _links.partners.href een antwoord")
    public void levert_een_GET_request_naar_de_url_in__links_partners_href_een_antwoord() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de geraadpleegde ingeschreven persoon heeft geen kinderen")
    public void de_geraadpleegde_ingeschreven_persoon_heeft_geen_kinderen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is _links.kinderen leeg")
    public void is__links_kinderen_leeg() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("in de registratie heeft de straatnaam van de verblijfplaats de waarde {string}")
    public void in_de_registratie_heeft_de_straatnaam_van_de_verblijfplaats_de_waarde(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de geboorteplaats van de ouder is niet bekend")
    public void de_geboorteplaats_van_de_ouder_is_niet_bekend() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de ouder wordt geraadpleegd")
    public void de_ouder_wordt_geraadpleegd() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("in de registratie heeft de geslachtsaanduiding de waarde {string} \\(onbekend)")
    public void in_de_registratie_heeft_de_geslachtsaanduiding_de_waarde_onbekend(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de persoon heeft een reisdocument met reisdocumentnummer {string}")
    public void de_persoon_heeft_een_reisdocument_met_reisdocumentnummer(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de persoon heeft geen ander actueel reisdocument")
    public void de_persoon_heeft_geen_ander_actueel_reisdocument() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent een verblijfplaats.datumVestigingInNederland = {int}")
    public void de_registratie_ingeschreven_persoon_kent_een_verblijfplaats_datumVestigingInNederland(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft in het antwoord verblijfplaats.datumVestigingInNederland.datum de waarde {int}{int}{int}")
    public void heeft_in_het_antwoord_verblijfplaats_datumVestigingInNederland_datum_de_waarde(Integer int1, Integer int2, Integer int3) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon kent een verblijfplaats.datumVestigingInNederland = {int}")
    public void de_registratie_ingeschreven_persoon_kent_een_verblijfplaats_datumVestigingInNederland(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent geen verblijfplaats.datumVestigingInNederland")
    public void de_registratie_ingeschreven_persoon_kent_geen_verblijfplaats_datumVestigingInNederland(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent een verblijfplaats.landVanwaarIngeschreven = {int}")
    public void de_registratie_ingeschreven_persoon_kent_een_verblijfplaats_landVanwaarIngeschreven(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent geen verblijfplaats.landVanwaarIngeschreven")
    public void de_registratie_ingeschreven_persoon_kent_geen_verblijfplaats_landVanwaarIngeschreven(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon {int} kent een verblijfplaats.verblijfBuitenland.land = {int}")
    public void de_registratie_ingeschreven_persoon_kent_een_verblijfplaats_verblijfBuitenland_land(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven persoon {int} is ingeschreven in een Nederlandse gemeente")
    public void de_registratie_ingeschreven_persoon_is_ingeschreven_in_een_Nederlandse_gemeente(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is het aantal gevonden ouders {int}")
    public void is_het_aantal_gevonden_ouders(Integer aantal) {
        assertThat(world.ouderHalCollectie, is(not(nullValue())));
        assertThat(world.ouderHalCollectie.getEmbedded(), is(not(nullValue())));
        assertThat(world.ouderHalCollectie.getEmbedded().getOuders(), hasSize(aantal));
    }

    @Gegeven("op de PL van een ingeschreven persoon is categorie ouder leeg met uitzondering van de registergemeenteAkte \\({double}), aktenummer \\({double}), datumIngangGeldigheid \\({double}), datumVanOpneming \\({double}) kenmerken")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_ouder_leeg_met_uitzondering_van_de_registergemeenteAkte_aktenummer_datumIngangGeldigheid_datumVanOpneming_kenmerken(Double double1, Double double2, Double double3, Double double4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("zijn er geen links naar de onbekende ouders")
    public void zijn_er_geen_links_naar_de_onbekende_ouders() {
        // ?
    }

    @Gegeven("op de PL van een ingeschreven persoon is categorie ouder leeg met uitzondering van de gemeenteDocument \\({double}), datumDocument \\({double}), beschrijvingDocument \\({double}), datumIngangGeldigheid \\({double}), datumVanOpneming \\({double}) kenmerken")
    public void op_de_PL_van_een_ingeschreven_persoon_is_categorie_ouder_leeg_met_uitzondering_van_de_gemeenteDocument_datumDocument_beschrijvingDocument_datumIngangGeldigheid_datumVanOpneming_kenmerken(Double double1, Double double2, Double double3, Double double4, Double double5) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt de ouder gevonden met burgerservicenummer={int}")
    public void wordt_de_ouder_gevonden_met_burgerservicenummer(Integer int1) {

    }

    @Als("ingeschreven personen gezocht worden zonder parameters")
    public void ingeschreven_personen_gezocht_worden_zonder_parameters() throws URISyntaxException {
        callRestTemplate(null);
    }

    @Gegeven("het reisdocument is een nog geldige identiteitskaart")
    public void het_reisdocument_is_een_nog_geldige_identiteitskaart() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("het reisdocument geraadpleegd met reisdocumentnummer {int}")
    public void het_reisdocument_geraadpleegd_met_reisdocumentnummer(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt een ingeschrevenpersonen link gevonden naar \\/ingeschrevenpersonen\\/{int}")
    public void wordt_een_ingeschrevenpersonen_link_gevonden_naar_ingeschrevenpersonen(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("het reisdocument is vermist gemeld op {int} april {int}")
    public void het_reisdocument_is_vermist_gemeld_op_april(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft één reisdocument met reisdocumentnummer {int}")
    public void de_te_raadplegen_persoon_heeft_één_reisdocument_met_reisdocumentnummer(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Als("de link reisdocumenten wordt gevolgd")
    public void de_link_reisdocumenten_wordt_gevolgd() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("is het aantal links naar reisdocumenten gelijk aan {int}")
    public void is_het_aantal_links_naar_reisdocumenten_gelijk_aan(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt een reisdocumenten link gevonden naar {int}")
    public void wordt_een_reisdocumenten_link_gevonden_naar(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft twee actuele reisducmenten \\({int} en {int})")
    public void de_te_raadplegen_persoon_heeft_twee_actuele_reisducmenten_en(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft een vermist reisdocument \\({int})")
    public void de_te_raadplegen_persoon_heeft_een_vermist_reisdocument(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft een reisdocument dat niet meer geldig is \\({int})")
    public void de_te_raadplegen_persoon_heeft_een_reisdocument_dat_niet_meer_geldig_is(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de registratie ingeschreven personen kent met de geslachtsnaam {string}, {string}, {string}, {string}")
    public void de_registratie_ingeschreven_personen_kent_met_de_geslachtsnaam(String string, String string2, String string3, String string4) {
        // hoeft geen data geinsert te worden, want zit al in de database
    }

    @Gegeven("de registratie ingeschreven personen kent met de voornamen {string}, {string}, {string}, {string}, {string}")
    public void de_registratie_ingeschreven_personen_kent_met_de_voornamen(String string, String string2, String string3, String string4, String string5) {
        // hoeft geen data geinsert te worden, want zit al in de database
    }

    @Als("de persoon wordt geraadpleegd")
    public void de_persoon_wordt_geraadpleegd() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

}

package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * PartnersStepDefinitions
 */
@Slf4j
public class PartnersStepDefinitions extends Defaults {

    @Gegeven("de te raadplegen persoon heeft geen \\(actuele) partner")
    public void de_te_raadplegen_persoon_heeft_geen_actuele_partner() {
    }

    @Gegeven("de te raadplegen persoon heeft een partner die zelf ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_een_partner_die_zelf_ingeschreven_persoon_is() {
    }

    @Gegeven("de partner van de ingeschreven persoon heeft in de registratie burgerservicenummer {}, voornaam Franklin en geslachtsnaam Groenen")
    public void de_partner_van_de_ingeschreven_persoon_heeft_in_de_registratie_burgerservicenummer_voornaam_Franklin_en_geslachtsnaam_Groenen(final String bsn) throws SQLException, ClassNotFoundException {
        testDataController.insertIntoPartnerschap(2L, 4L, "20191003", "H", null);
    }

    @Dan("wordt de partner gevonden met burgerservicenummer={}")
    public void wordt_de_partner_gevonden_met_burgerservicenummer(final String bsn) {
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get(), is(notNullValue()));
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get().getBurgerservicenummer(), is(notNullValue()));
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get().getBurgerservicenummer(), is(bsn));
    }

    @Dan("heeft deze partner naam.voornamen={}")
    public void heeft_deze_partner_naam_voornamen(final String voornamen) {
        List<Object> partnerList = world.partnerHalCollectie.getEmbedded().getPartners()
                .stream()
                .filter(p -> p.getNaam().getVoornamen().equals(voornamen))
                .collect(Collectors.toList());

        assertThat(partnerList.size(), is(1));
    }

    @Dan("heeft deze partner naam.geslachtsnaam={}")
    public void heeft_deze_partner_naam_geslachtsnaam_Groenen(final String geslachtsnaam) {
        List<Object> partnerList = world.partnerHalCollectie.getEmbedded().getPartners()
                .stream()
                .filter(p -> p.getNaam().getGeslachtsnaam().equals(geslachtsnaam))
                .collect(Collectors.toList());

        assertThat(partnerList.size(), is(1));
    }

    @Dan("heeft de gevonden partner link ingeschrevenpersonen met \\/ingeschrevenpersonen\\/{}")
    public void heeft_de_gevonden_partner_link_ingeschrevenpersonen_met_ingeschrevenpersonen(final String bsn) {
        final String link = BASE + port + "/ingeschrevenpersonen/" + bsn;
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get().getLinks().getIngeschrevenPersoon(), is(notNullValue()));
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get().getLinks().getIngeschrevenPersoon().getHref().toString(), is(link));
    }

    @Gegeven("de te raadplegen persoon heeft een partner die zelf geen ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_een_partner_die_zelf_geen_ingeschreven_persoon_is() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de partner van de ingeschreven persoon heeft volgens categorie {int}\\/{int} naam Dieter von Weit, geboren in Würzburg en geboortedatum {int} juni {int}")
    public void de_partner_van_de_ingeschreven_persoon_heeft_volgens_categorie_naam_Dieter_von_Weit_geboren_in_Würzburg_en_geboortedatum_juni(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft deze partner naam.voorvoegsel={}")
    public void heeft_deze_partner_naam_voorvoegsel_von(final String voorvoegsel) {
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().stream().findFirst().get().getNaam().getVoorvoegsel(), is(voorvoegsel));
    }

    @Dan("heeft de gevonden partner een lege link ingeschrevenpersonen")
    public void heeft_de_gevonden_partner_een_lege_link_ingeschrevenpersonen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft meerdere \\(twee) actuele partners \\(Ali en Iskander)")
    public void de_te_raadplegen_persoon_heeft_meerdere_twee_actuele_partners_Ali_en_Iskander() throws SQLException, ClassNotFoundException {
        testDataController.insertIntoPersoon("100000001", "Ali", "Extrapersoon", "20190916", "20190916");
        testDataController.insertIntoPersoon("100000010", "Iskander", "Extrapersoon", "20190916", "20190916");
        testDataController.insertIntoPartnerschap(39L, testDataController.findIdByBsn("100000001"), "20191003", "P", null);
        testDataController.insertIntoPartnerschap(39L, testDataController.findIdByBsn("100000010"), "20191003", "P", null);
    }

    @Dan("wordt de partner gevonden met naam.voornamen={}")
    public void wordt_de_partner_gevonden_met_naam_voornamen(final String voornamen) {
        List<Object> partnerList = world.partnerHalCollectie.getEmbedded().getPartners()
                .stream()
                .filter(p -> p.getNaam().getVoornamen().equals(voornamen))
                .collect(Collectors.toList());

        assertThat(partnerList.size(), is(1));
    }

    @Gegeven("de te raadplegen persoon heeft een beëindigde relatie")
    public void de_te_raadplegen_persoon_heeft_een_beëindigde_relatie() {
    }

    @Gegeven("de te raadplegen persoon heeft geen andere of nieuwe relatie")
    public void de_te_raadplegen_persoon_heeft_geen_andere_of_nieuwe_relatie() {
    }

    @Als("de link partners wordt gevolgd")
    public void de_link_partners_wordt_gevolgd() throws URISyntaxException {
        assertThat(world.persoonHal.getLinks().getPartners(), Matchers.hasSize(1));
        ingeschrevenNatuurlijkPersoonRestTemplateUri(world.persoonHal.getLinks().getPartners().stream().findFirst().get().getHref());
    }

}

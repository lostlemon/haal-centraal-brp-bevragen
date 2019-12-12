package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * PartnersOudersKinderenStepDefinitions
 */
public class PartnersOudersKinderenStepDefinitions extends Defaults {

    @Gegeven("de geraadpleegde persoon heeft een partner met {double} gevuld en {double} leeg")
    public void de_geraadpleegde_persoon_heeft_een_partner_met_gevuld_en_leeg(final Double double1, final Double double2) {
    }

    @Gegeven("de geraadpleegde persoon heeft geen andere partner \\(actueel of historisch)")
    public void de_geraadpleegde_persoon_heeft_geen_andere_partner_actueel_of_historisch() {
    }

    @Dan("is _links.partners leeg")
    public void is__links_partners_leeg() {
        assertThat(world.persoonHal.getLinks().getPartners(), is(nullValue()));
    }

    @Dan("is _embedded.partners leeg")
    public void is__embedded_partners_leeg() {
        assertThat(world.persoonHal.getEmbedded().getPartners(), is(nullValue()));
    }

    @Als("de partners worden geraadpleegd van de ingeschreven persoon met burgerservicenummer {}")
    public void de_partners_worden_geraadpleegd_van_de_ingeschreven_persoon_met_burgerservicenummer(final String bsn) {
        ingeschrevenNatuurlijkPersoonPartners(bsn);
    }

    @Dan("is het aantal gevonden partners {int}")
    public void is_het_aantal_gevonden_partners(final Integer aantal) {
        assertThat(world.partnerHalCollectie.getEmbedded().getPartners().size(), is(aantal));
    }

    @Gegeven("Reden ontbinding huwelijk\\/geregistreerd partnerschap = N \\(Nietigverklaring)")
    public void reden_ontbinding_huwelijk_geregistreerd_partnerschap_N_Nietigverklaring() {
    }

    @Dan("is het aantal links naar partners gelijk aan {int}")
    public void is_het_aantal_links_naar_partners_gelijk_aan(final Integer aantal) {
        if (aantal == 0) {
            assertThat(world.persoonHal.getLinks().getPartners(), is(nullValue()));
        } else {
            assertThat(world.persoonHal.getLinks().getPartners().size(), is(aantal));
        }
    }

    @Dan("is het aantal embedded partners gelijk aan {int}")
    public void is_het_aantal_embedded_partners_gelijk_aan(final Integer aantal) {
        if (aantal == 0) {
            assertThat(world.persoonHal.getEmbedded().getPartners(), is(nullValue()));
        } else {
            assertThat(world.persoonHal.getEmbedded().getPartners().size(), is(aantal));
        }
    }

    @Gegeven("de geraadpleegde persoon heeft een partner met {double} leeg en {double} gevuld")
    public void de_geraadpleegde_persoon_heeft_een_partner_met_leeg_en_gevuld(final Double double1, final Double double2) {
    }

    @Gegeven("de geraadpleegde persoon heeft twee partners met {double} leeg en {double} leeg")
    public void de_geraadpleegde_persoon_heeft_twee_partners_met_leeg_en_leeg(final Double double1, final Double double2) {
    }

    @Gegeven("de geraadpleegde persoon heeft ouder{int} met {int}\\/{double} leeg")
    public void de_geraadpleegde_persoon_heeft_ouder_met_leeg(final Integer int1, final Integer int2, final Double double1) {
    }

    @Gegeven("de geraadpleegde persoon heeft ouder{int} met {int}\\/{double} Onjuist leeg")
    public void de_geraadpleegde_persoon_heeft_ouder_met_Onjuist_leeg(final Integer int1, final Integer int2, final Double double1) {
    }

    @Gegeven("de geraadpleegde persoon heeft geen andere ouder\\(s)")
    public void de_geraadpleegde_persoon_heeft_geen_andere_ouder_s() {
    }

    @Dan("is het aantal links naar ouders gelijk aan {int}")
    public void is_het_aantal_links_naar_ouders_gelijk_aan(final Integer aantal) {
        if (aantal == 0) {
            assertThat(world.persoonHal.getLinks().getOuders(), is(nullValue()));
        } else {
            assertThat(world.persoonHal.getLinks().getOuders().size(), is(aantal));
        }
    }

    @Dan("is het aantal embedded ouders gelijk aan {int}")
    public void is_het_aantal_embedded_ouders_gelijk_aan(final Integer aantal) {
        if (aantal == 0) {
            assertThat(world.persoonHal.getEmbedded().getOuders(), is(nullValue()));
        } else {
            assertThat(world.persoonHal.getEmbedded().getOuders().size(), is(aantal));
        }
    }

    @Als("de ouders worden geraadpleegd van de ingeschreven persoon met burgerservicenummer {}")
    public void de_ouders_worden_geraadpleegd_van_de_ingeschreven_persoon_met_burgerservicenummer(final String bsn) {
        ingeschrevenNatuurlijkPersoonOuders(bsn);
    }

    @Gegeven("de geraadpleegde persoon heeft ouder{int} met {int}\\/{double} gevuld")
    public void de_geraadpleegde_persoon_heeft_ouder_met_gevuld(final Integer int1, final Integer int2, final Double double1) {
    }

    @Dan("heeft elke van de gevonden ouders ouder_aanduiding={int}")
    public void heeft_elke_van_de_gevonden_ouders_ouder_aanduiding(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft geen van de gevonden ouders ouder_aanduiding={int}")
    public void heeft_geen_van_de_gevonden_ouders_ouder_aanduiding(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de geraadpleegde persoon heeft drie kinderen met {int}\\/{double} Onjuist leeg")
    public void de_geraadpleegde_persoon_heeft_drie_kinderen_met_Onjuist_leeg(final Integer int1, final Double double1) {
    }

    @Gegeven("de geraadpleegde persoon heeft geen andere kinderen")
    public void de_geraadpleegde_persoon_heeft_geen_andere_kinderen() {
    }

    @Dan("is het aantal links naar kinderen gelijk aan {int}")
    public void is_het_aantal_links_naar_kinderen_gelijk_aan(final Integer aantal) {
        if (aantal == 0) {
            assertThat(world.persoonHal.getLinks().getKinderen(), is(nullValue()));
        } else {
            assertThat(world.persoonHal.getLinks().getKinderen().size(), is(aantal));
        }
    }

    @Dan("is het aantal embedded kinderen gelijk aan {int}")
    public void is_het_aantal_embedded_kinderen_gelijk_aan(final Integer aantal) {
        if (aantal == 0) {
            assertThat(world.persoonHal.getEmbedded().getKinderen(), is(nullValue()));
        } else {
            assertThat(world.persoonHal.getEmbedded().getKinderen().size(), is(aantal));
        }
    }

    @Als("de kinderen worden geraadpleegd van de ingeschreven persoon met burgerservicenummer {}")
    public void de_kinderen_worden_geraadpleegd_van_de_ingeschreven_persoon_met_burgerservicenummer(final String bsn) {
        ingeschrevenNatuurlijkPersoonKinderen(bsn);
    }

    @Dan("is het aantal gevonden kinderen {int}")
    public void is_het_aantal_gevonden_kinderen(final Integer aantal) {
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().size(), is(aantal));
    }

    @Gegeven("de geraadpleegde persoon heeft een kind met {int}\\/{double} gevuld \\(Franka)")
    public void de_geraadpleegde_persoon_heeft_een_kind_met_gevuld_Franka(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de geraadpleegde persoon heeft een kind met {int}\\/{double} leeg \\(Frank)")
    public void de_geraadpleegde_persoon_heeft_een_kind_met_leeg_Frank(Integer int1, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("heeft geen van de gevonden kinderen naam.voornamen={}")
    public void heeft_geen_van_de_gevonden_kinderen_naam_voornamen_Franka(final String voornamen) {
        List<Object> kindList = world.kindHalCollectie.getEmbedded().getKinderen()
                .stream()
                .filter(k -> k.getNaam().getVoornamen().equals(voornamen))
                .collect(Collectors.toList());

        assertThat(kindList.size(), is(0));
    }

}

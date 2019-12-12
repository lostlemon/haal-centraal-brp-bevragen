package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import nl.vng.realisatie.haalcentraal.core.enums.AdellijkeTitelPredikaat;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHal;
import org.hamcrest.Matchers;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * KinderenStepDefinitions
 */
public class KinderenStepDefinitions extends Defaults {

    private IngeschrevenPersoonHal kind = null;

    @Gegeven("de te raadplegen persoon heeft geen \\(geregistreerde) kinderen")
    public void de_te_raadplegen_persoon_heeft_geen_geregistreerde_kinderen() {
    }

    @Gegeven("de te raadplegen persoon heeft een kind dat zelf ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_een_kind_dat_zelf_ingeschreven_persoon_is() {
    }

    @Gegeven("het kind van de ingeschreven persoon heeft in de registratie burgerservicenummer {}, naam {}" + "\t" + "Groenen, geboren op {} {word} {}")
    public void het_kind_van_de_ingeschreven_persoon_heeft_in_de_registratie_burgerservicenummer_naam_Franklin_Groenen_geboren_op_mei(final String bsn, final String naam,
                                                                                                                                      final String dag, final String maand,
                                                                                                                                      final String jaar) throws SQLException, ClassNotFoundException {
        testDataController.insertIntoAfstamming(47L, 4L, "OUDER1", "20001021");
    }

    @Dan("wordt het kind gevonden met burgerservicenummer={}")
    public void wordt_het_kind_gevonden_met_burgerservicenummer(final String bsn) {
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getBurgerservicenummer(), is(bsn));
    }

    @Dan("heeft dit kind naam.voornamen={}")
    public void heeft_dit_kind_naam_voornamen(final String voornamen) {
        List<Object> kindList = world.kindHalCollectie.getEmbedded().getKinderen()
                .stream()
                .filter(k -> k.getNaam().getVoornamen().equals(voornamen))
                .collect(Collectors.toList());

        assertThat(kindList.size(), is(1));
    }

    @Dan("heeft dit kind naam.voorvoegsel={}")
    public void heeft_dit_kind_naam_voorvoegsel(final String voorvoegsel) {
        if (voorvoegsel.equals("null")) {
            assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getNaam().getVoorvoegsel(), is(nullValue()));
        } else {
            List<Object> kindList = world.kindHalCollectie.getEmbedded().getKinderen()
                    .stream()
                    .filter(k -> k.getNaam().getVoorvoegsel().equals(voorvoegsel))
                    .collect(Collectors.toList());

            assertThat(kindList.size(), is(1));
        }
    }

    @Dan("heeft dit kind naam.geslachtsnaam={}")
    public void heeft_dit_kind_naam_geslachtsnaam(final String geslachtsnaam) {
        List<Object> kindList = world.kindHalCollectie.getEmbedded().getKinderen()
                .stream()
                .filter(k -> k.getNaam().getGeslachtsnaam().equals(geslachtsnaam))
                .collect(Collectors.toList());

        assertThat(kindList.size(), is(1));
    }

    @Dan("heeft dit kind naam.adellijkeTitel_predikaat.omschrijvingAdellijkeTitel_predikaat={}")
    public void heeft_dit_kind_naam_adellijkeTitel_predikaat_omschrijvingAdellijkeTitel_predikaat_null(final String adellijkeTitel) {
        kind = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersoon(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getBurgerservicenummer(), null, null, null);
        if (adellijkeTitel.equals("null")) {
            assertThat(Arrays.stream(AdellijkeTitelPredikaat.values())
                    .map(AdellijkeTitelPredikaat::getOmschrijving)
                    .map(String::toLowerCase).anyMatch(s -> !kind.getNaam().getAanschrijfwijze().contains(s)), is(true));
        } else {
            assertThat(kind.getNaam().getAanschrijfwijze().toLowerCase().contains(adellijkeTitel.toLowerCase()), is(true));
        }
    }

    @Dan("heeft dit kind geboorte.datum.datum={}")
    public void heeft_dit_kind_geboorte_datum_datum(final String datum) {

        final LocalDate formattedDatum = LocalDate.parse(datum, DateTimeFormatter.ISO_LOCAL_DATE);
        List<Object> kindList = world.kindHalCollectie.getEmbedded().getKinderen()
                .stream()
                .filter(k -> k.getGeboorte().getDatum().getDatum().equals(formattedDatum))
                .collect(Collectors.toList());

        assertThat(kindList.size(), is(1));
    }

    @Dan("heeft dit gevonden kind de ingeschrevenpersonen link met \\/ingeschrevenpersonen\\/{}")
    public void heeft_dit_gevonden_kind_de_ingeschrevenpersonen_link_met_ingeschrevenpersonen(final String bsn) {
        final String link = BASE + port + "/ingeschrevenpersonen/" + bsn;
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getLinks().getIngeschrevenPersoon(), is(notNullValue()));
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getLinks().getIngeschrevenPersoon().getHref().toString(), is(link));
    }

    @Gegeven("de te raadplegen persoon heeft een kind die zelf geen ingeschreven persoon is")
    public void de_te_raadplegen_persoon_heeft_een_kind_die_zelf_geen_ingeschreven_persoon_is() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("het kind van de ingeschreven persoon heeft volgens categorie {int}\\/{int} naam Chantal Groenen, geboren in Istanbul en geboortedatum {int} januari {int}")
    public void het_kind_van_de_ingeschreven_persoon_heeft_volgens_categorie_naam_Chantal_Groenen_geboren_in_Istanbul_en_geboortedatum_januari(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dan("wordt het kind gevonden met naam.voornamen={}")
    public void wordt_het_kind_gevonden_met_naam_voornamen_Lisa(final String voornamen) {
        List<Object> kindList = world.kindHalCollectie.getEmbedded().getKinderen()
                .stream()
                .filter(k -> k.getNaam().getVoornamen().equals(voornamen))
                .collect(Collectors.toList());

        assertThat(kindList.size(), is(1));
    }

    @Dan("heeft dit kind burgerservicenummer={}")
    public void heeft_dit_kind_burgerservicenummer_null(final String bsn) {
        if (bsn.equals("null")) {
            assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getBurgerservicenummer(), is(nullValue()));
        } else {
            assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getBurgerservicenummer(), is(bsn));
        }
    }

    @Dan("heeft dit kind geboorte.datum.dag={int}")
    public void heeft_dit_kind_geboorte_datum_dag(final Integer dag) {
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getGeboorte().getDatum().getDag(), is(dag));
    }

    @Dan("heeft dit kind geboorte.datum.maand={int}")
    public void heeft_dit_kind_geboorte_datum_maand(final Integer maand) {
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getGeboorte().getDatum().getMaand(), is(maand));
    }

    @Dan("heeft dit kind geboorte.plaats={}")
    public void heeft_dit_kind_geboorte_plaats_Istanbul(final String plaats) {
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getGeboorte().getPlaats().getOmschrijving(), is(plaats));
    }

    @Dan("heeft dit kind geboorte.land.landnaam={}")
    public void heeft_dit_kind_geboorte_land_landnaam_Turkije(final String land) {
        assertThat(world.kindHalCollectie.getEmbedded().getKinderen().stream().findFirst().get().getGeboorte().getPlaats().getOmschrijving(), is(land));
    }

    @Dan("heeft dit gevonden kind een lege link ingeschrevenpersonen")
    public void heeft_dit_gevonden_kind_een_lege_link_ingeschrevenpersonen() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Gegeven("de te raadplegen persoon heeft meerdere \\(twee) kinderen \\(Lisa en Daan)")
    public void de_te_raadplegen_persoon_heeft_meerdere_twee_kinderen_Lisa_en_Daan() throws SQLException, ClassNotFoundException {
        testDataController.insertIntoAfstamming(4L, 49L, "OUDER1", "20001021");
        testDataController.insertIntoAfstamming(4L, 50L, "OUDER1", "20001021");
    }

    @Gegeven("de te raadplegen persoon heeft drie kinderen \\(Jip, Chantal en Jesse)")
    public void de_te_raadplegen_persoon_heeft_drie_kinderen_Jip_Chantal_en_Jesse() throws SQLException, ClassNotFoundException {
        testDataController.insertIntoPersoon("100000001", "Chantal", "Extrapersoon", "20190916", "20190916");
        testDataController.insertIntoPersoon("100000010", "Jesse", "Extrapersoon", "20190916", "20190916");
        testDataController.insertIntoAfstamming(39L, 51L, "OUDER1", "20001021");
        testDataController.insertIntoAfstamming(39L, testDataController.findIdByBsn("100000001"), "OUDER1", "20001021");
        testDataController.insertIntoAfstamming(39L, testDataController.findIdByBsn("100000010"), "OUDER1", "20001021");
    }

    @Als("de link kinderen wordt gevolgd")
    public void de_link_kinderen_wordt_gevolgd() throws URISyntaxException {
        assertThat(world.persoonHal.getLinks().getKinderen(), Matchers.hasSize(1));
        ingeschrevenNatuurlijkPersoonRestTemplateUri(world.persoonHal.getLinks().getKinderen().stream().findFirst().get().getHref());
    }

}

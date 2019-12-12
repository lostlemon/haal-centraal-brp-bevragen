package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.enums.OpschortingEnum;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.DatumOnvolledig;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.Geboorte;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.GeslachtEnum;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.HalLink;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonLinks;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.Naam;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.OuderHal;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.RedenOpschortingBijhoudingEnum;
import org.assertj.core.util.Arrays;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * FieldsStepDefinitions
 */

@Slf4j
public class FieldsStepDefinitions extends Defaults {

    @Gegeven("de registratie ingeschreven personen kent zo(a|A)ls beschreven in testdata.csv")
    public void de_registratie_ingeschreven_personen_kent_zoals_beschreven_in_testdata_csv() {
    }

    @Gegeven("in onderstaande scenario's wordt de expand parameter niet gebruikt, tenzij expliciet aangegeven")
    public void in_onderstaande_scenario_s_wordt_de_expand_parameter_niet_gebruikt_tenzij_expliciet_aangegeven() {
    }

    @Gegeven("de gebruiker geautoriseerd is voor gevraagde gegevens, tenzij expliciet anders aangegeven")
    public void de_gebruiker_geautoriseerd_is_voor_gevraagde_gegevens_tenzij_expliciet_anders_aangegeven() {
    }

    @Als("een ingeschreven persoon wordt geraadpleegd zonder fields-parameter")
    public void een_ingeschreven_persoon_wordt_geraadpleegd_zonder_fields_parameter() throws URISyntaxException {
        ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, null, null);
    }

    @Dan("worden alle attributen van de persoon teruggegeven, voor zover ze een waarde hebben")
    public void worden_alle_attributen_van_de_persoon_teruggegeven_voor_zover_ze_een_waarde_hebben() {
        worden_alle_attributen_van_de_resource_teruggegeven();
    }

    @Dan("worden alle attributen van de resource teruggegeven")
    public void worden_alle_attributen_van_de_resource_teruggegeven() {

        assertThat(world.persoonHal.getBurgerservicenummer(), is(notNullValue()));
        assertThat(world.persoonHal.getGeslachtsaanduiding(), is(notNullValue()));
        //assertThat(world.persoonHal.getDatumEersteInschrijvingGBA(), is(notNullValue())); zit niet in de testdata
        assertThat(world.persoonHal.getKiesrecht(), is(notNullValue()));
        assertThat(world.persoonHal.getGeboorte(), is(notNullValue()));
        assertThat(world.persoonHal.getNaam(), is(notNullValue()));
        assertThat(world.persoonHal.getVerblijfplaats(), is(notNullValue()));
    }

    @Dan("worden alle relaties van de resource teruggegeven")
    public void worden_alle_relaties_van_de_resource_teruggegeven() {
        assertThat(world.persoonHal.getLinks().getOuders(), is(notNullValue()));
        assertThat(world.persoonHal.getLinks().getKinderen(), is(notNullValue()));
        assertThat(world.persoonHal.getLinks().getPartners(), is(notNullValue()));
    }

    @Dan("wordt er geen gerelateerde sub-resource teruggegeven in _embedded")
    public void wordt_er_geen_gerelateerde_sub_resource_teruggegeven_in__embedded() {
        assertThat(world.persoonHal.getEmbedded(), is(nullValue()));
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
        return query_pairs;
    }

    @Als("een ingeschreven persoon wordt geraadpleegd met {}")
    public void een_ingeschreven_persoon_wordt_geraadpleegd_met_query(final String query) throws UnsupportedEncodingException {
        final Map<String, String> map = splitQuery(query);
        ingeschrevenNatuurlijkPersoon(DEFAULT_BSN, map.get("expand"), map.get("fields"));
    }

    @Als("bsn {word} wordt geraadpleegd met {}")
    public void bsn_x_wordt_geraadpleegd_met_query(final String bsn, final String query) throws UnsupportedEncodingException {
        final Map<String, String> map = splitQuery(query);
        ingeschrevenNatuurlijkPersoon(bsn, map.get("expand"), map.get("fields"));
    }

    @Dan("wordt attribuut {} teruggegeven")
    public void wordt_attribuut_x_teruggegeven(final String field) {

        Map<String, Function<IngeschrevenPersoonHal, ?>> map = new HashMap<>();

        map.put("geslachtsaanduiding", IngeschrevenPersoonHal::getGeslachtsaanduiding);
        map.put("naam", p -> p.getNaam());
        map.put("naam.geslachtsnaam", p -> p.getNaam() != null ? p.getNaam().getGeslachtsnaam() : null);
        map.put("naam.voorvoegsel", p -> p.getNaam() != null ? p.getNaam().getVoorvoegsel() : null);
        map.put("naam.voornamen", p -> p.getNaam() != null ? p.getNaam().getVoornamen() : null);
        map.put("naam.aanschrijfwijze", p -> p.getNaam() != null ? p.getNaam().getAanschrijfwijze() : null);
        map.put("geboorte.datum", p -> p.getGeboorte() != null ? p.getGeboorte().getDatum() : null);
        map.put("geboorte.plaats", p -> p.getGeboorte() != null ? p.getGeboorte().getPlaats() : null);
        map.put("geboorte.inOnderzoek.datum", p -> p.getGeboorte() != null ? p.getGeboorte().getInOnderzoek() != null ? p.getGeboorte().getInOnderzoek().getDatum() : null : null);
        map.put("_links.self", p -> p.getLinks() != null ? p.getLinks().getSelf() : null);
        map.put("burgerservicenummer", IngeschrevenPersoonHal::getBurgerservicenummer);
        map.put("datumVestigingInNederland", p -> p.getVerblijfplaats() != null ? p.getVerblijfplaats().getDatumVestigingInNederland() : null);
        map.put("burgerlijkeStaat", (p) -> "zit niet in de swagger file");
        map.put("datumOpschortingBijhouding", p -> p.getOpschortingBijhouding() != null ? p.getOpschortingBijhouding().getDatum() : null);
        map.put("redenOpschortingBijhouding", p -> p.getOpschortingBijhouding() != null ? p.getOpschortingBijhouding().getReden() : null);
        map.put("_links.kinderen", p -> p.getLinks() != null ? p.getLinks().getKinderen() : null);
        map.put("_links.partners", p -> p.getLinks() != null ? p.getLinks().getPartners() : null);
        map.put("_links.ouders", p -> p.getLinks() != null ? p.getLinks().getOuders() : null);
        map.put("_embedded.ouders", p -> p.getEmbedded() != null ? p.getEmbedded().getOuders() : null);
        map.put("_embedded.kinderen", p -> p.getEmbedded() != null ? p.getEmbedded().getKinderen() : null);
        map.put("_embedded.kinderen.burgerservicenummer", p -> p.getEmbedded().getKinderen().stream().anyMatch(k -> k.getBurgerservicenummer() == null) ? null : 1);
        map.put("_embedded.kinderen.geboorte", p -> p.getEmbedded().getKinderen().stream().anyMatch(k -> k.getGeboorte() == null) ? null : 1);
        map.put("_embedded.kinderen._links.ingeschrevenpersonen", p -> p.getEmbedded().getKinderen().stream().anyMatch(k -> k.getLinks() == null || k.getLinks().getIngeschrevenPersoon() == null) ? null : 1);
        map.put("_embedded.partners", p -> p.getEmbedded() != null ? p.getEmbedded().getPartners() : null);

        if (!map.containsKey(field)) {
            throw new IllegalArgumentException("unknown field " + field);
        }

        assertThat("field=" + field, map.get(field).apply(world.persoonHal), is(notNullValue()));
    }

    @Dan("is in het antwoord {}={}")
    public void is_in_het_antwoord_x_is_y(String field, String string) {

        final Map<String, java.util.function.Supplier<?>> map = new HashMap<>();

        map.put("code", () -> world.validatieFoutbericht.getCode());
        map.put("status", () -> world.statusCode);
        map.put("title", () -> world.validatieFoutbericht.getTitle());
        map.put("invalid-params.name", () -> world.validatieFoutbericht.getInvalidParams().stream().findFirst().get().getName());
        map.put("invalid-params.reason", () -> world.validatieFoutbericht.getInvalidParams().stream().findFirst().get().getReason());
        map.put("invalid-params.code", () -> world.validatieFoutbericht.getInvalidParams().stream().findFirst().get().getCode());

        if (world.persoonHal != null) {
            map.put("geboorte.inOnderzoek.datum", () -> world.persoonHal.getGeboorte().getInOnderzoek().getDatum());
            map.put("geboorte.inOnderzoek.plaats", () -> world.persoonHal.getGeboorte().getInOnderzoek().getPlaats());
            map.put("geboorte.inOnderzoek.land", () -> world.persoonHal.getGeboorte().getInOnderzoek().getLand());
            map.put("naam.inOnderzoek.voornamen", () -> world.persoonHal.getNaam().getInOnderzoek().getVoornamen());
//        map.put("naam.inOnderzoek.adellijkeTitel_predikaat", p -> p.getNaam().getInOnderzoek().get);
            map.put("naam.inOnderzoek.voorvoegsel", () -> world.persoonHal.getNaam().getInOnderzoek().getVoorvoegsel());
            map.put("naam.inOnderzoek.geslachtsnaam", () -> world.persoonHal.getNaam().getInOnderzoek().getGeslachtsnaam());
//        map.put("naam.inOnderzoek.aanduidingNaamgebruik", p -> p.getNaam().getInOnderzoek().get);        
            map.put("burgerservicenummer", () -> world.persoonHal.getBurgerservicenummer());
            map.put("naam.voornamen", () -> world.persoonHal.getNaam().getVoornamen());
            map.put("naam.voorvoegsel", () -> world.persoonHal.getNaam().getVoorvoegsel());
            map.put("naam.geslachtsnaam", () -> world.persoonHal.getNaam().getGeslachtsnaam());
            map.put("naam.adellijkeTitel_predikaat.omschrijvingAdellijkeTitel_predikaat", () -> world.persoonHal.getNaam().getAanschrijfwijze());
            map.put("geboorte.datum.jaar", () -> world.persoonHal.getGeboorte().getDatum().getJaar());
            map.put("geboorte.datum.dag", () -> world.persoonHal.getGeboorte().getDatum().getDag());
            map.put("geboorte.datum.maand", () -> world.persoonHal.getGeboorte().getDatum().getMaand());
            map.put("geboorte.datum.datum", () -> world.persoonHal.getGeboorte().getDatum());
            map.put("geslachtsaanduiding", () -> world.persoonHal.getGeslachtsaanduiding());
            map.put("datumVestigingInNederland", () -> world.persoonHal.getVerblijfplaats().getDatumVestigingInNederland());
            map.put("datumOpschortingBijhouding.datum", () -> world.persoonHal.getOpschortingBijhouding().getDatum().getDatum());
            map.put("redenOpschortingBijhouding", () -> world.persoonHal.getOpschortingBijhouding().getReden());

        } else if (world.ouderHalCollectie != null) {
            map.put("burgerservicenummer", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getBurgerservicenummer).collect(Collectors.toList()));
            map.put("naam.voornamen", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getNaam).map(Naam::getVoornamen).collect(Collectors.toList()));
            map.put("naam.voorvoegsel", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getNaam).map(Naam::getVoorvoegsel).collect(Collectors.toList()));
            map.put("naam.geslachtsnaam", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getNaam).map(Naam::getGeslachtsnaam).collect(Collectors.toList()));

            map.put("geboorte.datum.jaar", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getGeboorte).map(Geboorte::getDatum).map(DatumOnvolledig::getJaar).collect(Collectors.toList()));
            map.put("geboorte.datum.dag", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getGeboorte).map(Geboorte::getDatum).map(DatumOnvolledig::getDag).collect(Collectors.toList()));
            map.put("geboorte.datum.maand", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getGeboorte).map(Geboorte::getDatum).map(DatumOnvolledig::getMaand).collect(Collectors.toList()));
            map.put("geboorte.datum.datum", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getGeboorte).map(Geboorte::getDatum).map(DatumOnvolledig::getDatum).collect(Collectors.toList()));
            map.put("geslachtsaanduiding", () -> world.ouderHalCollectie.getEmbedded().getOuders().stream().map(OuderHal::getGeslachtsaanduiding).collect(Collectors.toList()));
        }
        if (!map.containsKey(field)) {
            throw new IllegalArgumentException("Unknown field: " + field + ", world=" + world);
        }

        final Object actual = map.get(field).get();
        if (actual != null) {
            assertThat(actual, is(not(nullValue())));
            switch (actual.getClass().getSimpleName()) {
                case "String": {
                    assertThat("field=" + field, actual, is(string));
                    break;
                }
                case "Boolean": {
                    assertThat("field=" + field, actual, is(Boolean.valueOf(string)));
                    break;
                }
                case "Integer": {
                    assertThat("field=" + field, actual, is(Integer.valueOf(string)));
                    break;
                }
                case "LocalDate": {
                    assertThat("field=" + field, actual, is(convertDatum(string)));
                    break;
                }
                case "GeslachtEnum": {
                    assertThat("field=" + field, ((GeslachtEnum) actual).name(), is(string));
                    break;
                }
                case "DatumOnvolledig": {
                    assertThat("field=" + field, ((DatumOnvolledig) actual).getDatum(), is(convertDatum(string)));
                    break;
                }
                case "RedenOpschortingBijhoudingEnum": {
                    assertThat("field=" + field, ((RedenOpschortingBijhoudingEnum) actual).getValue(), is(OpschortingEnum.fromCode(string).name().toLowerCase()));
                    break;
                }
                case "ArrayList": {
                    if (((ArrayList) actual).size() > 0 && !((ArrayList) actual).contains(null)) {
                        switch (((ArrayList) actual).get(0).getClass().getSimpleName()) {
                            case "String": {
                                assertThat("field=" + field, ((ArrayList) actual).contains(string), is(true));
                                break;
                            }
                            case "Integer": {
                                assertThat("field=" + field, ((ArrayList) actual).contains(Integer.valueOf(string)), is(true));
                                break;
                            }
                            case "LocalDate": {
                                assertThat("field=" + field, ((ArrayList) actual).contains(convertDatum(string)), is(true));
                                break;
                            }
                            case "GeslachtEnum": {
                                assertThat("field=" + field, ((ArrayList) actual).contains(GeslachtEnum.fromValue(string)), is(true));
                                break;
                            }
                            default: {
                                throw new IllegalArgumentException("Unknown type: " + ((ArrayList) actual).get(0).getClass());
                            }
                        }
                    } else {
                        assertThat("field=" + field, ((ArrayList) actual).stream().allMatch(Objects::isNull), is(true));
                    }
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown type: " + actual.getClass());
                }
            }
        } else {
            assertTrue(Objects.isNull(actual));
        }
    }

    @Dan("wordt geen enkel ander attribuut dan {} teruggegeven")
    public void wordt_geen_enkel_ander_attribuut_dan_x(final String attributen) {

        final Set<String> set = Arrays.asList(attributen.split(",| en "))
                .stream()
                .filter(Objects::nonNull)
                .map(String.class::cast)
                .map(s -> s.replace(" ", ""))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toSet());


        Map<String, Function<IngeschrevenPersoonHal, ?>> map = new HashMap<>();

        map.put("burgerservicenummer", IngeschrevenPersoonHal::getBurgerservicenummer);
        map.put("geslachtsaanduiding", IngeschrevenPersoonHal::getGeslachtsaanduiding);
//        map.put("burgelijkeStaat", (i) -> "zit niet in swagger file");
        map.put("naam", IngeschrevenPersoonHal::getNaam);
        map.put("_links", IngeschrevenPersoonHal::getLinks);
        map.put("_links.self", (i) -> i.getLinks().getSelf());
        map.put("_embedded", IngeschrevenPersoonHal::getEmbedded);
        map.put("indicatieGeheim", IngeschrevenPersoonHal::getGeheimhoudingPersoonsgegevens);
        map.put("datumOpschortingBijhouding", (i) -> {
            return i.getOpschortingBijhouding() != null ? i.getOpschortingBijhouding().getDatum() : null;
        });
        map.put("redenOpschortingBijhouding", p -> p.getOpschortingBijhouding() != null ? p.getOpschortingBijhouding().getReden() : null);
        set //
                .stream() //
                .filter(s -> !map.keySet().contains(s)) //
                .filter(s -> !s.equals("burgerlijkeStaat")) // "burgelijkeStaat" zit niet in swagger file
                .forEach((s) -> {
                    throw new IllegalArgumentException("Unknown field: " + s);
                });

        map.forEach((attribuut, source)
                -> {

            // _links en _links.self altijd tonen
            final boolean shouldBePresent = set.contains(attribuut)
                    || (attribuut.equals("_links.self"))
                    || (attribuut.equals("_links"));


            assertThat("checking " + attribuut + " for presence: " + shouldBePresent, source.apply(world.persoonHal),
                    is(shouldBePresent ? not(nullValue()) : nullValue())
            );
        });
    }

    @Dan("wordt in _links geen enkel ander attribuut dan {} teruggegeven")
    public void wordt_in__links_geen_enkel_ander_attribuut_dan_x(final String attributen) {

        final Set<String> set = Arrays.asList(attributen.split(",| en "))
                .stream()
                .filter(Objects::nonNull)
                .map(String.class::cast)
                .map(s -> s.replace(" ", ""))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toSet());


        Map<String, Function<IngeschrevenPersoonLinks, ?>> map = new HashMap<>();

        map.put("self", IngeschrevenPersoonLinks::getSelf);
        map.put("partners", IngeschrevenPersoonLinks::getPartners);
        map.put("ouders", IngeschrevenPersoonLinks::getOuders);
        map.put("kinderen", IngeschrevenPersoonLinks::getKinderen);

        set //
                .stream() //
                .filter(s -> !map.keySet().contains(s)) //
                .forEach((s) -> {
                    throw new IllegalArgumentException("Unknown field: " + s);
                });

        map.forEach((attribuut, source)
                -> {
            // _links en _links.self altijd tonen
            final boolean shouldBePresent = set.contains(attribuut);

            assertThat("checking " + attribuut + " for presence: " + shouldBePresent, source.apply(world.persoonHal.getLinks()),
                    is(shouldBePresent ? not(nullValue()) : nullValue())
            );
        });
    }

    @Dan("wordt geen enkele relatie van de resource in _links teruggegeven")
    public void wordt_geen_enkele_relatie_van_de_resource_in__links_teruggegeven() {
        assertThat(world.persoonHal.getLinks().getKinderen(), is(nullValue()));
        assertThat(world.persoonHal.getLinks().getOuders(), is(nullValue()));
        assertThat(world.persoonHal.getLinks().getPartners(), is(nullValue()));
    }

    @Gegeven("de te raadplegen persoon heeft voornamen, geslachtsnaam en voorvoegsel")
    public void de_te_raadplegen_persoon_heeft_voornamen_geslachtsnaam_en_voorvoegsel() {
        // gegeven in testset
    }

    @Dan("wordt in naam geen enkel ander attribuut dan aanschrijfwijze teruggegeven")
    public void wordt_in_naam_geen_enkel_ander_attribuut_dan_aanschrijfwijze_teruggegeven() {
        assertThat(world.persoonHal.getNaam().getVoorvoegsel(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getGeslachtsnaam(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getVoornamen(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getVoorletters(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getAanhef(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getGebruikInLopendeTekst(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getInOnderzoek(), is(nullValue()));
    }

    @Dan("wordt in naam geen enkel ander attribuut dan voorvoegsel en geslachtsnaam teruggegeven")
    public void wordt_in_naam_geen_enkel_ander_attribuut_dan_voorvoegsel_en_geslachtsnaam_teruggegeven() {
        assertThat(world.persoonHal.getNaam().getAanschrijfwijze(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getVoornamen(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getVoorletters(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getAanhef(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getGebruikInLopendeTekst(), is(nullValue()));
        assertThat(world.persoonHal.getNaam().getInOnderzoek(), is(nullValue()));
    }

    @Gegeven("de te raadplegen persoon heeft een actuele partner\\(partnerschap of huwelijk), ouders en kinderen")
    public void de_te_raadplegen_persoon_heeft_een_actuele_partner_partnerschap_of_huwelijk_ouders_en_kinderen() {
        // opgenomen in de testdata
    }

    @Gegeven("de te raadplegen persoon heeft een BAG-adres \\(nummeraanduiding) als verblijfplaats")
    public void de_te_raadplegen_persoon_heeft_een_BAG_adres_nummeraanduiding_als_verblijfplaats() {
        // opgenomen in de testdata
    }

    @Dan("is elke link {word} een geldige uri")
    public void is_elke_link_kinderen_een_geldige_uri(String groep) {

        final Map<String, Function<IngeschrevenPersoonLinks, List<HalLink>>> map = new HashMap<>();

        map.put("kinderen", IngeschrevenPersoonLinks::getKinderen);
        map.put("ouders", IngeschrevenPersoonLinks::getOuders);
        map.put("partners", IngeschrevenPersoonLinks::getPartners);

        map
                .get(groep)
                .apply(world.persoonHal.getLinks())
                .stream()
                .map(l -> l.getHref())
                .forEach(l -> assertThat(l, is(notNullValue())));
        ;
    }

    @Dan("wordt in _embedded geen enkel ander attribuut dan kinderen teruggegeven")
    public void wordt_in__embedded_geen_enkel_ander_attribuut_dan_kinderen_teruggegeven() {
        assertThat(world.persoonHal.getEmbedded().getKinderen(), is(not(nullValue()))); // kan zijn dat example geen kinderen heeft
        assertThat(world.persoonHal.getEmbedded().getOuders(), is(nullValue()));
        assertThat(world.persoonHal.getEmbedded().getPartners(), is(nullValue()));
    }

    @Dan("levert dit alle attributen die een waarde hebben en waarvoor autorisatie is")
    public void levert_dit_alle_attributen_die_een_waarde_hebben_en_waarvoor_autorisatie_is() {
        assertThat(world.persoonHal.getBurgerservicenummer(), is(not(nullValue())));
    }

    @Gegeven("de te raadplegen persoon heeft Datum opschorting bijhouding = {string}")
    public void de_te_raadplegen_persoon_heeft_Datum_opschorting_bijhouding(String string) {
        ingeschrevenNatuurlijkPersoon("999992326", null, null);
    }

    @Gegeven("de te raadplegen persoon heeft Omschrijving reden opschorting bijhouding \\({double}) = {string} \\(emigratie)")
    public void de_te_raadplegen_persoon_heeft_Omschrijving_reden_opschorting_bijhouding_emigratie(Double double1, String string) {
    }

}

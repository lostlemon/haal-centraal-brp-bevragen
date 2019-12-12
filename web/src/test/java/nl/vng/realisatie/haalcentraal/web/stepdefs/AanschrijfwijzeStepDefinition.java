package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * AanschrijvwijzeStepDefinition
 */
public class AanschrijfwijzeStepDefinition extends Defaults {

    final static Map<String, Set<String>> givenMapping = new HashMap<>();
    public final static String AANGAAN_DATUM = "11111111";

    static {
        //met voorvoegsel

        givenMapping.put("999999102", new HashSet<>(Arrays.asList("Henk", "Veld", "E")));
        givenMapping.put("999999114", new HashSet<>(Arrays.asList("Ingrid", "Velzen", "N")));
        givenMapping.put("999999126", new HashSet<>(Arrays.asList("Suzanne", "Veld", "P")));
        givenMapping.put("999999138", new HashSet<>(Arrays.asList("Fred", "Velzen", "V")));

        //zonder voorvoegsel

        givenMapping.put("999999047", new HashSet<>(Arrays.asList("Franklin", "Groenen", "E")));
        givenMapping.put("999999060", new HashSet<>(Arrays.asList("Franka", "Groenen", "N")));
        givenMapping.put("999999035", new HashSet<>(Arrays.asList("Johan Frank Robert", "Groenink", "P")));
        givenMapping.put("999999023", new HashSet<>(Arrays.asList("Franka", "Groenlo", "V")));

        //betrokkene heeft een adelijke titel en partner heeft geen adellijke titel

        givenMapping.put("555555021", new HashSet<>(Arrays.asList("Hendrik Willem", "Aedel", "E")));
        givenMapping.put("555555112", new HashSet<>(Arrays.asList("Wilhelmina", "Aedel", "N")));
        givenMapping.put("555555379", new HashSet<>(Arrays.asList("Frederique", "Aedel", "P")));
        givenMapping.put("555555719", new HashSet<>(Arrays.asList("Emma Louise", "Aedel", "V")));

        //partner heeft een predikaat

        givenMapping.put("999999199", new HashSet<>(Arrays.asList("Sjaak", "Berg", "E")));
        givenMapping.put("999999205", new HashSet<>(Arrays.asList("Peter", "Berg", "N")));
        givenMapping.put("999999217", new HashSet<>(Arrays.asList("Marlies", "Berg", "P")));
        givenMapping.put("999999229", new HashSet<>(Arrays.asList("Fleur", "Berg", "V")));
        //

        givenMapping.put("999999163", new HashSet<>(Arrays.asList("Anna Cornelia", "Veen", "E")));

    }

    @Gegeven("een ingeschreven persoon met {}, {}, {}")
    public void eenIngeschrevenPersoonMet(final String voornamen, final String geslachtsnaam, final String aanduidingAanschrijving) throws URISyntaxException {
        switch (findBsnByVoornamenAndGeslachtsnaamInPersoon(voornamen, geslachtsnaam)) {
            case 999999102:
            case 999999114:
                insertInPartnerschap(10L, 11L);
                insertInPartnerschap(11L, 10L);
                break;
            case 999999126:
            case 999999138:
                insertInPartnerschap(12L, 13L);
                insertInPartnerschap(13L, 12L);
                break;
            case 999999060:
            case 999999035:
                insertInPartnerschap(6L, 3L);
                insertInPartnerschap(3L, 6L);
                break;
            case 999999047:
            case 999999023:
                insertInPartnerschap(2L, 4L);
                insertInPartnerschap(4L, 2L);
                break;
            case 555555112:
                updatePersoon(findIdFromBsnInPersoon(555555112), "adellijketitelPredikaat", "GI");
                insertInPartnerschap(14L, 28L);
                insertInPartnerschap(28L, 14L);
                break;
            case 555555379:
                insertInPartnerschap(29L, 15L);
                insertInPartnerschap(15L, 29L);
                break;
            case 555555719:
                final Long partner_id = findIdFromBsnInPersoon(555555719);
                updatePersoon(partner_id, "adellijketitelPredikaat", "GI");
                insertInPartnerschap(partner_id, 16L);
                insertInPartnerschap(16L, partner_id);
                break;
            case 999999205:
                insertInPartnerschap(31L, 19L);
                insertInPartnerschap(19L, 31L);
                break;
            case 999999217:
                insertInPartnerschap(20L, 31L);
                insertInPartnerschap(31L, 20L);
                break;
            case 999999229:
                insertInPartnerschap(21L, 31L);
                insertInPartnerschap(31L, 21L);
                break;
            case 999999163:
                updatePersoon(27L, "adellijketitelPredikaat", "BS");
                switch (aanduidingAanschrijving) {
                    case "N":
                        updatePersoon(findIdFromBsnInPersoon(999999163), "aanduidingAanschrijving", "N");
                        insertInPartnerschap(15L, 27L);
                        insertInPartnerschap(27L, 15L);
                        break;
                    case "P":
                        updatePersoon(findIdFromBsnInPersoon(999999163), "aanduidingAanschrijving", "P");
                        insertInPartnerschap(15L, 27L);
                        insertInPartnerschap(27L, 15L);
                        break;
                    case "V":
                        updatePersoon(findIdFromBsnInPersoon(999999163), "aanduidingAanschrijving", "V");
                        insertInPartnerschap(15L, 27L);
                        insertInPartnerschap(27L, 15L);
                        break;
                    default:
                }
                break;
            case 999999151:
                updatePersoon(29L, "adellijketitelPredikaat", "G");
                switch (aanduidingAanschrijving) {
                    case "N":
                        updatePersoon(findIdFromBsnInPersoon(999999151), "aanduidingAanschrijving", "N");
                        insertInPartnerschap(14L, 29L);
                        insertInPartnerschap(29L, 14L);
                        break;
                    case "P":
                        updatePersoon(findIdFromBsnInPersoon(999999151), "aanduidingAanschrijving", "P");
                        insertInPartnerschap(14L, 29L);
                        insertInPartnerschap(29L, 14L);
                        break;
                    case "V":
                        updatePersoon(findIdFromBsnInPersoon(999999151), "aanduidingAanschrijving", "V");
                        insertInPartnerschap(14L, 29L);
                        insertInPartnerschap(29L, 14L);
                        break;
                    default:
                }
            case 555555021:
                updatePersoon(findIdFromBsnInPersoon(555555021), "adellijketitelPredikaat", "G");
                break;
            default:
                insertInPartnerschap(31L, 10L);
                insertInPartnerschap(10L, 31L);
        }
    }

    @Als("ingeschreven persoon wordt geraadpleegd met {}, {}, {}")
    public void ingeschrevenPersoonWordtGeraadpleegd(final String voornamen, final String geslachtsnaam, final String aanduidingAanschrijving) throws URISyntaxException {
        givenMapping.entrySet()
            .stream()
            .filter(m -> m.getValue().containsAll(Arrays.asList(voornamen, geslachtsnaam)))
            .findFirst()
            .ifPresent(i -> ingeschrevenNatuurlijkPersoon(i.getKey(), null, null));
    }

    @Dan("heeft de gevonden ingeschrevenpersoon naam.aanschrijfwijze={}")
    public void heeftDeGevondenIngeschrevenpersoonNaamAanschrijfwijze(final String waarde) {
        assertThat(world.persoonHal.getNaam().getAanschrijfwijze(), is(waarde));
    }

}

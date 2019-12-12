package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.After;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;

import static nl.vng.realisatie.haalcentraal.web.stepdefs.AanschrijfwijzeStepDefinition.AANGAAN_DATUM;
import static nl.vng.realisatie.haalcentraal.web.stepdefs.OudersStepDefinition.GEBOORTE_DATUM;
import static nl.vng.realisatie.haalcentraal.web.stepdefs.OudersStepDefinition.INGANG_DATUM;


/**
 * Hooks
 */
public class Hooks extends Defaults {

    final int[] types = new int[]{Types.VARCHAR};

    @After
    public void afterScenario() {
        deleteFromPartnerschap(new Object[]{"20191003"}, "datumOntbinding");
        deleteFromAfstamming(new Object[]{"20001021"}, "datumIngangFamilierechtelijkeBetrekking");
        deleteFromPersoon(new Object[]{"Extrapersoon"}, "geslachtsnaam");
        deleteFromAfstamming(new Object[]{INGANG_DATUM}, "datumIngangFamilierechtelijkeBetrekking");
        deleteFromPartnerschap(new Object[]{AANGAAN_DATUM}, "aangaanHuwelijkPartnerschapDatum");
        deleteFromPersoon(new Object[]{GEBOORTE_DATUM}, "geboorteDatum");
    }

    private void deleteFromPartnerschap(final Object[] objects, final String field) {
        final String query = "DELETE FROM PARTNERSCHAP WHERE " + field + " = ? ";
        excuteQuery(query, objects);
    }

    private void deleteFromPersoon(final Object[] objects, final String field) {
        String query = "DELETE FROM Persoon WHERE " + field + " = ? ";
        excuteQuery(query, objects);
    }

    private void deleteFromAfstamming(final Object[] objects, final String field) {
        final String query = "DELETE FROM AFSTAMMING WHERE " + field + " = ? ";
        excuteQuery(query, objects);
    }

    private void excuteQuery(final String query, final Object[] objects) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update(query, objects, types);
    }

}
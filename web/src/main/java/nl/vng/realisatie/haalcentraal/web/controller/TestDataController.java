package nl.vng.realisatie.haalcentraal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

/**
 * TestDataController
 */
@Service
public class TestDataController {

    @Autowired
    private DataSource dataSource;

    public void insertIntoPersoon(final String bsn, final String voornamen, final String geslachtsnaam, final String datumEersteInschrijvingGBA, final String geboorteDatum) throws ClassNotFoundException, SQLException {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{UUID.randomUUID().toString(), bsn, voornamen, geslachtsnaam, datumEersteInschrijvingGBA, geboorteDatum};
        final int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        final String query = "INSERT INTO PERSOON(uuid, bsn, voornamen, geslachtsnaam, datumEersteInschrijvingGBA, geboorteDatum) " + "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(query, params, types);
    }

    public void insertIntoPartnerschap(final Long partner1Id, final Long partner2Id, final String datumOntbinding, final String soortVerbintenis, final String indicatieOnjuist) throws ClassNotFoundException, SQLException {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{UUID.randomUUID().toString(), partner1Id, partner2Id, datumOntbinding, soortVerbintenis, indicatieOnjuist};
        final int[] types = new int[]{Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        final String query = "INSERT INTO PARTNERSCHAP(uuid, partner1_id, partner2_id, datumOntbinding, soortVerbintenis, indicatieOnjuist) " + "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(query, params, types);
    }

    public void insertIntoAfstamming(final Long ouderId, final Long kindId, final String ouderAanduidingEnum, final String datumIngangFamilierechtelijkeBetrekking) throws ClassNotFoundException, SQLException {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{UUID.randomUUID().toString(), ouderId, kindId, ouderAanduidingEnum, datumIngangFamilierechtelijkeBetrekking};
        final int[] types = new int[]{Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.VARCHAR};
        final String query = "INSERT INTO AFSTAMMING(uuid, ouder_id, kind_id, ouderAanduidingEnum, datumIngangFamilierechtelijkeBetrekking) " + "VALUES (?, ?, ?, ?, ?)";
        template.update(query, params, types);
    }

    public Long findIdByBsn(final String bsn) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final String query = "SELECT ID FROM PERSOON WHERE BSN ='" + bsn + "'";
        return template.queryForObject(query, Long.class);
    }

    public void updateOverlijden(final String bsn, final String overlijdenDatum) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final String query = "UPDATE PERSOON SET overlijdenDatum='" + overlijdenDatum + "'" + "WHERE BSN ='" + bsn + "'";
        template.update(query);
    }

}

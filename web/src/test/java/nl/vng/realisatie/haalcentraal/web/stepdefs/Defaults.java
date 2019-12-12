package nl.vng.realisatie.haalcentraal.web.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.restclient.generated.api.bip.IngeschrevenpersonenApi;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.Foutbericht;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHalCollectie;
import nl.vng.realisatie.haalcentraal.web.controller.BevraagPersoonController;
import nl.vng.realisatie.haalcentraal.web.controller.ServiceConfig;
import nl.vng.realisatie.haalcentraal.web.controller.TestDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

import static nl.vng.realisatie.haalcentraal.web.stepdefs.AanschrijfwijzeStepDefinition.AANGAAN_DATUM;
import static nl.vng.realisatie.haalcentraal.web.stepdefs.OudersStepDefinition.INGANG_DATUM;

/**
 * Defaults
 */

@Slf4j
public abstract class Defaults {

    public final static int port = 8081;

    @Autowired
    protected IngeschrevenpersonenApi ingeschrevenpersonenApi;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected ServiceConfig serviceConfig;

    @Autowired
    protected TestDataController testDataController;

    @Autowired
    protected World world;

    public final static String BASE = "http://127.0.0.1:";

    public final static String DEFAULT_BSN = "999993677";

    protected void ingeschrevenNatuurlijkPersoon(String bsn, String expand, String fields) {

        world.clean();

        log.debug("rest call: ingeschrevenNatuurlijkPersoon(bsn=" + bsn + ", expand=" + expand + ", fields=" + fields + ")");

        try {
            world.persoonHal = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersoon(bsn, null, expand, fields);
            world.statusCode = HttpStatus.OK.value();
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
            try {
                world.validatieFoutbericht = new ObjectMapper().readValue(e.getResponseBodyAsByteArray(), Foutbericht.class);
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
        }
    }

    protected void ingeschrevenNatuurlijkPersoonRestTemplate(final String bsn, final String query) throws URISyntaxException {
        final URI uri = new URI(BASE + port + "/ingeschrevenpersonen/" + bsn + (StringUtils.hasText(query) ? "?" + query : ""));
        ingeschrevenNatuurlijkPersoonRestTemplateUri(uri);
    }

    protected void ingeschrevenNatuurlijkPersoonRestTemplateUri(final URI uri) throws URISyntaxException {

        world.clean();

        log.debug("rest call: " + uri);

        try {
            world.statusCode = HttpStatus.OK.value();
            world.persoonHal = restTemplate.getForObject(uri, IngeschrevenPersoonHal.class);
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
            try {
                world.validatieFoutbericht = new ObjectMapper().readValue(e.getResponseBodyAsByteArray(), Foutbericht.class);
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
        }
    }

    protected void ingeschrevenNatuurlijkPersonen(String bsn, String fields, String expand) {

        world.clean();

        try {
            world.persoonCollectie = ingeschrevenpersonenApi.ingeschrevenNatuurlijkPersonen(null, expand, bsn, fields, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            world.statusCode = HttpStatus.OK.value();
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
            try {
                world.validatieFoutbericht = new ObjectMapper().readValue(e.getResponseBodyAsByteArray(), Foutbericht.class);
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
        }
    }

    protected void ingeschrevenNatuurlijkPersoonPartners(String bsn) {

        world.clean();

        log.debug("rest call: ingeschrevenNatuurlijkPersoonPartners(bsn=" + bsn);

        try {
            world.partnerHalCollectie = ingeschrevenpersonenApi.ingeschrevenpersonenBurgerservicenummerpartners(bsn, null);
            world.statusCode = HttpStatus.OK.value();
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
        }
    }

    protected void ingeschrevenNatuurlijkPersoonOuders(String bsn) {

        world.clean();

        log.debug("rest call: ingeschrevenNatuurlijkPersoonOuders(bsn=" + bsn);

        try {
            world.ouderHalCollectie = ingeschrevenpersonenApi.ingeschrevenpersonenBurgerservicenummerouders(bsn, null);
            world.statusCode = HttpStatus.OK.value();
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
        }
    }

    protected void ingeschrevenNatuurlijkPersoonKinderen(String bsn) {

        world.clean();

        log.debug("rest call: ingeschrevenNatuurlijkPersoonOuders(bsn=" + bsn);

        try {
            world.kindHalCollectie = ingeschrevenpersonenApi.ingeschrevenpersonenBurgerservicenummerkinderen(bsn, null);
            world.statusCode = HttpStatus.OK.value();
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
        }
    }

    protected void callRestTemplate(final String query) throws URISyntaxException {

        world.clean();

        final URI uri = new URI(BASE + port + "/ingeschrevenpersonen" + (StringUtils.hasText(query) ? query : ""));

        log.debug("rest call: " + uri);

        try {
            world.persoonCollectie = restTemplate.getForObject(uri, IngeschrevenPersoonHalCollectie.class);
            world.statusCode = HttpStatus.OK.value();
        } catch (HttpClientErrorException e) {
            world.statusCode = e.getRawStatusCode();
            try {
                world.validatieFoutbericht = new ObjectMapper().readValue(e.getResponseBodyAsByteArray(), Foutbericht.class);
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
        }
    }

    public LocalDate convertDatum(final String datum) {
        return LocalDate.parse(datum, BevraagPersoonController.QUERYFORMATTER);
    }

    public Long findIdFromBsnInPersoon(final Integer bsn) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final String selectQuery = "SELECT id FROM PERSOON p WHERE p.bsn = " + bsn;
        return template.queryForObject(selectQuery, Long.class);
    }

    public Long findIdFromVoornamenInPersoon(final String voornamen) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final String selectQuery = "SELECT id FROM PERSOON p WHERE p.voornamen = '" + voornamen + "'";
        return template.queryForObject(selectQuery, Long.class);
    }

    public Long findIdFromGeslachtsnaamInPersoon(final String geslachtsnaam) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final String selectQuery = "SELECT id FROM PERSOON p WHERE p.geslachtsnaam = '" + geslachtsnaam + "'";
        return template.queryForObject(selectQuery, Long.class);
    }

    public void insertInAfstamming(final Long kindId, final Long ouderId, final String ouderAanduiding, final String ingangDatum) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{ouderId, kindId, ouderAanduiding, INGANG_DATUM, UUID.randomUUID().toString()};
        final int[] types = new int[]{Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        final String query = "INSERT INTO AFSTAMMING(ouder_id, kind_id, ouderAanduidingEnum, datumIngangFamilierechtelijkeBetrekking, uuid) " + "VALUES (?, ?, ?, ?, ?)";
        template.update(query, params, types);
        log.debug(String.valueOf(template.queryForObject("select count(*) from Afstamming where ouder_id =" + ouderId, Integer.class)));
    }

    public void insertInPersoon(final String voornamen, final Integer bsn, final String geslachtsnaam, final String voorvoegsel, final String aanduidingAanschrijving, final String adellijkPredikaat, final String geboortePlaats, final String geboorteLand, final String geboorteDatum) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{bsn, UUID.randomUUID().toString(), voornamen, geslachtsnaam, voorvoegsel, aanduidingAanschrijving, adellijkPredikaat, geboortePlaats, geboorteDatum, geboorteLand};
        final int[] types = new int[]{Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        final String query = "INSERT INTO Persoon(bsn, uuid, voornamen, geslachtsnaam, voorvoegsel, aanduidingAanschrijving, adellijketitelPredikaat, geboortePlaats, geboorteDatum, geboorteLand) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(query, params, types);

    }

    public void updatePersoon(final Long id, final String field, final String value) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{value, id};
        final int[] types = new int[]{Types.VARCHAR, Types.BIGINT};
        final String query = "UPDATE PERSOON SET " + field + " = ? WHERE id = ?";
        template.update(query, params, types);
    }

    public void insertInPartnerschap(final Long partner1_id, final Long partner2_id) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{partner1_id, partner2_id, AANGAAN_DATUM, UUID.randomUUID().toString()};
        final int[] types = new int[]{Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.VARCHAR};
        final String query = "INSERT INTO PARTNERSCHAP(partner1_id,partner2_id, aangaanHuwelijkPartnerschapDatum, uuid) " + "VALUES (?, ?, ?, ?)";
        template.update(query, params, types);
    }

    public Integer findBsnByVoornamenAndGeslachtsnaamInPersoon(final String voornamen, final String geslachtsnaam) {
        final JdbcTemplate template = new JdbcTemplate(dataSource);
        final Object[] params = new Object[]{voornamen, geslachtsnaam};
        final int[] types = new int[]{Types.VARCHAR, Types.VARCHAR};
        final String query = "SELECT bsn FROM PERSOON WHERE voornamen = '" + voornamen + "' AND geslachtsnaam = '" + geslachtsnaam + "'";
        return template.queryForObject(query, Integer.class);
    }

}

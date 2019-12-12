package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.web.HaalCentraal;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * CucumberSpringContextConfiguration
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {HaalCentraal.class, TestConfiguration.class}, loader = SpringBootContextLoader.class)
@ActiveProfiles("citest")
public class CucumberSpringContextConfiguration {

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
     */
    @Before
    public void setUp() {
        log.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }

}
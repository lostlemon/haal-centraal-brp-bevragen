package nl.vng.realisatie.haalcentraal.web;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberReportRunner.class)
@CucumberOptions(features = "classpath:features", plugin = {"pretty", "json:target/cucumber-report.json"}, tags = "not @skip_scenario")
public class CucumberTest {

}
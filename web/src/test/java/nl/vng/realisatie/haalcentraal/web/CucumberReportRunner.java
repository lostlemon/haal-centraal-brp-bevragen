package nl.vng.realisatie.haalcentraal.web;

import io.cucumber.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to generate html report from cucumber runner's json report output.
 */

public class CucumberReportRunner extends Cucumber {

    private static final String PROJECT_NAME = "HaalCentraal";

    @Value(value = "${projectVersion}")
    private String projectVersion;

    @Value(value = "${git.branche}")
    private String branche;

    @Value(value = "${buildtimestamp}")
    private String buildtimestamp;

    public CucumberReportRunner(final Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        generateReport();
    }

    private void generateReport() {

        final File reportOutputDirectory = new File("target/cucumber/pretty");
        final List<String> jsonFiles = new ArrayList<>();

        jsonFiles.add("target/cucumber-report.json");

        Configuration configuration = new Configuration(reportOutputDirectory, PROJECT_NAME);
        configuration.setBuildNumber(projectVersion + " @ " + buildtimestamp);
        configuration.addClassifications("Build Number", configuration.getBuildNumber());
        configuration.addClassifications("Branch Name", branche);

        configuration.setSortingMethod(SortingMethod.NATURAL);
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}
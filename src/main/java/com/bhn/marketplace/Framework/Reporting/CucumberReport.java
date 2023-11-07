package com.bhn.marketplace.Framework.Reporting;

import com.bhn.marketplace.Framework.Logger.Log;
import com.bhn.marketplace.Framework.Utils.PropertyReader;
import io.cucumber.java.Scenario;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CucumberReport {

    private static final String TestReportName = PropertyReader.getGlobalValue("TEST_REPORT_NAME");
    private static final String ApiURL = PropertyReader.getEnvValue("API_URL");
    private static final String ApiUsername = PropertyReader.getGlobalValue("API_USERNAME");

    public static Scenario scenario;

    public static void logOrderNumber(String orderNumber) {
        scenario.log("Order Number: " + orderNumber);
    }

    public static void generateReport() {

        File reportOutputDirectory = new File("target/report");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/report/cucumber.json");

        Configuration configuration = new Configuration(reportOutputDirectory, TestReportName);
        //configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
        configuration.addClassifications("Environment", PropertyReader.getExecutionEnvironment());
        configuration.addClassifications("API URL", ApiURL);
        configuration.addClassifications("API User", ApiUsername);

        configuration.addCustomJsFiles(Collections.singletonList("src/main/resources/CustomReportUI.js"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

        Log.info("Test Report published successfully.");
    }
}

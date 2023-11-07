package com.bhn.marketplace;

import com.bhn.marketplace.Framework.Logger.Log;
import com.bhn.marketplace.Framework.Reporting.CucumberReport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberManager {

    @Before()
    public static void initializeScenario(Scenario scenario) {
        CucumberReport.scenario = scenario;
        Log.startScenario(CucumberReport.scenario);
    }

    @After()
    public static void tearDownScenario() {
        Log.endScenario();
    }

}

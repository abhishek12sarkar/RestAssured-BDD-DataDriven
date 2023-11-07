package com.bhn.marketplace;

import com.bhn.marketplace.Framework.API.ApiTestBase;
import com.bhn.marketplace.Framework.Database.DatabaseManager;
import com.bhn.marketplace.Framework.Reporting.CucumberReport;
import com.bhn.marketplace.Framework.Utils.PropertyReader;
import com.bhn.marketplace.Framework.Utils.TestDataManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"com.bhn.marketplace"}
)

public class TestRunner {

    @BeforeClass()
    public static void setupUser() {
        PropertyReader.setExecutionEnvironment();
        ApiTestBase.setLogPrintStream();
        ApiTestBase.assignTenantIdToUser();
    }


    @AfterClass()
    public static void suiteTearDown() {
        TestDataManager.tearDownTestDataManager();
        DatabaseManager.disconnectDB();
        CucumberReport.generateReport();
    }
}
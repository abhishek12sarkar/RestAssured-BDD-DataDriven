package com.bhn.marketplace.Framework.API;

import com.bhn.marketplace.Framework.Database.DatabaseManager;
import com.bhn.marketplace.Framework.Logger.Log;
import com.bhn.marketplace.Framework.Utils.PropertyReader;
import com.bhn.marketplace.Framework.Utils.RandomValueGenerator;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.io.IoBuilder;

import java.io.PrintStream;

public class ApiTestBase extends Log {
    protected final String Api_Url = PropertyReader.getEnvValue("API_URL");
    private final String Merchant_ID = PropertyReader.getGlobalValue("API_MERCHANT_ID");
    private final String Milliseconds_To_Wait = PropertyReader.getGlobalValue("MILLISECONDS_TO_WAIT");
    private final String Content_Type = PropertyReader.getGlobalValue("API_CONTENT_TYPE");
    private static final String Tenant_ID = PropertyReader.getGlobalValue("API_TENANT_ID");
    private static final String ApiUsername = PropertyReader.getGlobalValue("API_USERNAME");
    protected static RequestSpecification logSpec;


    /**
     * Setup Logger specification for rest assured
     */
    public static void setLogPrintStream() {
        PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
        RestAssuredConfig restLogConfig = new RestAssuredConfig();
        LogConfig logConfig = restLogConfig.getLogConfig();
        logConfig.defaultStream(logStream).enablePrettyPrinting(true);
        logSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(restLogFilter)
                .setConfig(restLogConfig)
                .build();
    }

    /**
     * Assign tenant ID to the user
     */
    public static void assignTenantIdToUser() {
        DatabaseManager.executeSQL("update dbo.users set tenant_id='' where tenant_id='" + Tenant_ID + "';");
        DatabaseManager.executeSQL("update dbo.users set tenant_id='" + Tenant_ID + "' where username='" + ApiUsername + "';");
        Log.info("Tenant ID assigned to username " + ApiUsername);
    }

    /**
     * Set default headers for all requests.
     */
    protected RequestSpecification setDefaultHeaders(RequestSpecification request) {
        return request
                .header("MerchantId", Merchant_ID)
                .header("millisecondsToWait", Milliseconds_To_Wait)
                .header("RequestId", RandomValueGenerator.generateRequestID());
    }

    /**
     * Set default content type for all requests.
     */
    protected RequestSpecification setDefaultContentType(RequestSpecification request) {
        return request.contentType(Content_Type);
    }
}

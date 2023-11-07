package com.bhn.marketplace.Framework.Utils;

import com.bhn.marketplace.Framework.Logger.Log;
import org.junit.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final String propertiesDirectory = System.getProperty("user.dir") + "/configs/";
    private static final Properties globalProperty = new Properties();
    private static final Properties apiEndpointProperty = new Properties();
    private static final Properties envProperty = new Properties();
    private static String executionEnvironment;


    public static String getGlobalValue(String key) {
        FileReader propertyReader = null;
        try {
            propertyReader = new FileReader(propertiesDirectory + "GlobalConfig.properties");
            globalProperty.load(propertyReader);
        } catch (IOException fileException) {
            Assert.fail(fileException.getMessage());
        }
        return globalProperty.getProperty(key);
    }

    public static String getApiEndpoint(String key) {
        FileReader propertyReader = null;
        try {
            propertyReader = new FileReader(propertiesDirectory + "ApiEndpoints.properties");
            apiEndpointProperty.load(propertyReader);
        } catch (IOException fileException) {
            Assert.fail(fileException.getMessage());
        }
        return apiEndpointProperty.getProperty(key);
    }

    public static String getEnvValue(String key) {
        return readPropertyFile(key);
    }

    private static String readPropertyFile(String key) {
        FileReader propertyReader = null;
        try {
            propertyReader = new FileReader(propertiesDirectory + PropertyReader.getExecutionEnvironment() + "Env.properties");
            envProperty.load(propertyReader);
        } catch (IOException fileException) {
            Assert.fail(fileException.getMessage());
        }
        return envProperty.getProperty(key);
    }

    public static String getExecutionEnvironment() {
        return executionEnvironment;
    }

    public static void setExecutionEnvironment() {
        executionEnvironment = System.getProperty("env");
        if (executionEnvironment != null) {
            if (executionEnvironment.equals("Fog") || executionEnvironment.equals("Stratus")) {
                Log.info("Test execution started in " + executionEnvironment + " environment");
            } else {
                Log.fatal("Invalid execution environment. Values allowed are 'Fog' and 'Stratus'.");
                Assert.fail("Invalid execution environment. Values allowed are 'Fog' and 'Stratus'.");
            }
        } else {
            System.out.println("Couldn't find a defined execution environment to execute test cases." + "Executing tests in " + PropertyReader.getGlobalValue("DEFAULT_EXECUTION_ENVIRONMENT") + " environment.");
            Log.warn("Couldn't find a defined execution environment to execute test cases." + "Executing tests in " + PropertyReader.getGlobalValue("DEFAULT_EXECUTION_ENVIRONMENT") + " environment.");
            executionEnvironment = PropertyReader.getGlobalValue("DEFAULT_EXECUTION_ENVIRONMENT");
        }
    }
}

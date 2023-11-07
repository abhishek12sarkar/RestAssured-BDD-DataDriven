package com.bhn.marketplace.Framework.Logger;

import io.cucumber.java.Scenario;
import io.restassured.filter.Filter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log extends RestLogFilter {

    protected static final Logger logger = LogManager.getLogger();
    protected static final Filter restLogFilter = new RestLogFilter();

    public static void trace(String textToLog) {
        logger.trace(textToLog);
    }

    public static void info(String textToLog) {
        logger.info(textToLog);
    }

    public static void debug(String textToLog) {
        logger.debug(textToLog);
    }

    public static void warn(String textToLog) {
        logger.warn(textToLog);
    }

    public static void error(String textToLog) {
        logger.error(textToLog);
    }

    public static void fatal(String textToLog) {
        logger.fatal(textToLog);
    }

    public static void startScenario(Scenario scenario) {
        logger.info("Scenario started with ID: " + scenario.getId());
        logger.info("Scenario: " + scenario.getName());
        scenario.log("Scenario ID: " + scenario.getId());
    }

    public static void endScenario() {
        logger.info("---------------------------------SCENARIO END---------------------------------\n");
    }
}
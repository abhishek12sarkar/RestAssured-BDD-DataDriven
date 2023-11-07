package com.bhn.marketplace.Framework.Database;

import com.bhn.marketplace.Framework.Logger.Log;
import com.bhn.marketplace.Framework.Utils.PropertyReader;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String sqlServer = PropertyReader.getEnvValue("SQL_SERVER");
    private static final String database = PropertyReader.getEnvValue("DATABASE");
    private static final String username = PropertyReader.getEnvValue("DB_USERNAME");
    private static final String password = PropertyReader.getEnvValue("DB_PASSWORD");
    private static final String loginTimeout = PropertyReader.getEnvValue("DB_LOGIN_TIMEOUT");
    private static Connection connection = null;
    private static DatabaseManager databaseManager = null;

    private DatabaseManager() {
        try {
            String connectionURL = sqlServer + database + username + password + loginTimeout;
            connection = DriverManager.getConnection(connectionURL);
            Log.info("Connected to database successfully.");
        } catch (SQLException sqlException) {
            Assert.fail(sqlException.getMessage());
        }
    }

    public static Connection databaseConnection() {
        try {
            if (databaseManager == null) {
                databaseManager = new DatabaseManager();
            }
        } catch (Exception dbException) {
            Assert.fail(dbException.getMessage());
        }
        return connection;
    }

    public static void disconnectDB() {
        try {
            if (connection != null) {
                connection.close();
                Log.info("Database connection closed successfully.");
            }
        } catch (SQLException connectionException) {
            Log.error("Couldn't disconnect from Database: \n" + connectionException.getMessage());
        }
    }

    public static void executeSQL(String sqlQuery) {
        try {
            Statement sql = DatabaseManager.databaseConnection().createStatement();
            sql.executeUpdate(sqlQuery);
            sql.close();
        } catch (SQLException sqlException) {
            Assert.fail(sqlException.getMessage());
        }
    }

}

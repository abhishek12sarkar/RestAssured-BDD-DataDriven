package com.bhn.marketplace.Framework.Utils;

import com.bhn.marketplace.DataTransferObjects.TestDataObjects.EgiftOrderDetails;
import com.bhn.marketplace.DataTransferObjects.TestDataObjects.OrderLine;
import com.bhn.marketplace.Framework.Logger.Log;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestDataManager {
    private static TestDataManager testDataManager = null;
    private static File orderInputTestDataFile = null;
    private static FileInputStream orderInputTestDataInputStream;
    private static Workbook orderInputWorkBook = null;
    private static final String testDataDirectory = System.getProperty("user.dir") + "/TestData/";
    private static final DataFormatter dataFormatter = new DataFormatter();

    // Column indexes in Test Data excel file
    private static int
            clientProgramIndex,
            paymentTypeIndex,
            faidIndex,
            contentProviderIndex,
            minAmountIndex,
            maxAmountIndex,
            quantity,
            recipientFirstNameIndex,
            recipientLastNameIndex,
            emailIndex;


    private TestDataManager(String executionEnvironment) {
        try {
            if (orderInputTestDataFile == null) {
                orderInputTestDataFile = new File(testDataDirectory + "TestData_" + executionEnvironment + ".xlsx");
                orderInputTestDataInputStream = new FileInputStream(orderInputTestDataFile);
                orderInputWorkBook = new XSSFWorkbook(orderInputTestDataInputStream);
            }
        } catch (IOException fileException) {
            Assert.fail(fileException.getMessage());
        }
    }


    public static void tearDownTestDataManager() {
        try {
            if (testDataManager != null) {
                orderInputWorkBook.close();
                orderInputTestDataInputStream.close();
                Log.info("Test Data Manager is closed successfully.");
            }
        } catch (IOException testDataTearDownException) {
            Assert.fail("Failed to close Test Data file in location: " + orderInputTestDataFile.getPath());
        }

    }


    public static Workbook getExcelWorkBook() {
        if (testDataManager == null) testDataManager = new TestDataManager(PropertyReader.getExecutionEnvironment());
        return orderInputWorkBook;
    }


    public static EgiftOrderDetails getEgiftOrderDetails(String country, String paymentType) {
        Sheet sheet = TestDataManager.getExcelWorkBook().getSheet(country + " eGift");

        //Dynamic column index mapper
        Row headerRow = sheet.getRow(0);
        for (Cell headerCell : headerRow) {
            if (headerCell.getStringCellValue().equalsIgnoreCase("Client Program")) {
                clientProgramIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Payment Type")) {
                paymentTypeIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("FAID")) {
                faidIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Content Providers")) {
                contentProviderIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Min Amount")) {
                minAmountIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Max Amount")) {
                maxAmountIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Quantity")) {
                quantity = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Recipient First Name")) {
                recipientFirstNameIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Recipient Last Name")) {
                recipientLastNameIndex = headerCell.getColumnIndex();
            }
            if (headerCell.getStringCellValue().equalsIgnoreCase("Email")) {
                emailIndex = headerCell.getColumnIndex();
            }
        }

        EgiftOrderDetails egiftOrderDetails = new EgiftOrderDetails();

        //Build eGift Test Data from Excel
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(paymentTypeIndex).toString().equalsIgnoreCase(paymentType)) {
                Cell clientProgramCell = row.getCell(clientProgramIndex);
                egiftOrderDetails.clientProgramNumber = dataFormatter.formatCellValue(clientProgramCell);
                egiftOrderDetails.faid = row.getCell(faidIndex).toString();
                egiftOrderDetails.paymentType = row.getCell(paymentTypeIndex).toString();
                String loadValue = RandomValueGenerator.generateLoadValueInRange(row.getCell(minAmountIndex).getNumericCellValue(), row.getCell(maxAmountIndex).getNumericCellValue());
                egiftOrderDetails.orderLine.add(new OrderLine(
                        row.getCell(contentProviderIndex).toString(),
                        loadValue, row.getCell(quantity).toString(),
                        row.getCell(recipientFirstNameIndex).toString(),
                        row.getCell(recipientLastNameIndex).toString(),
                        row.getCell(emailIndex).toString()));
            }
        }
        return egiftOrderDetails;
    }

    //Get Test Data values based on inputs
    public static String getTestDataInput(String input) {
        String value = "";
        Sheet sheet = TestDataManager.getExcelWorkBook().getSheet("Test Inputs");
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals(input)) {
                Cell valueCell = row.getCell(1);
                value = dataFormatter.formatCellValue(valueCell);
                break;
            }
        }
        return value;
    }

}

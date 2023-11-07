package com.bhn.marketplace.DataTransferObjects.TestDataObjects;

public class OrderLine {
    public String contentProviderCode;
    public String loadValue;
    public String quantity;
    public String recipientFirstName;
    public String recipientLastName;
    public String recipientEmail;

    public OrderLine(String contentProviderCode, String loadValue, String quantity, String recipientFirstName, String recipientLastName, String recipientEmail) {
        this.contentProviderCode = contentProviderCode;
        this.loadValue = loadValue;
        this.quantity = quantity;
        this.recipientFirstName = recipientFirstName;
        this.recipientLastName = recipientLastName;
        this.recipientEmail = recipientEmail;
    }
}

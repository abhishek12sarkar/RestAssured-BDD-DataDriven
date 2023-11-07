package com.bhn.marketplace.DataTransferObjects.TestDataObjects;

import java.util.ArrayList;

public class EgiftOrderDetails {

    public String clientProgramNumber;
    public String faid;
    public String paymentType;
    public ArrayList<OrderLine> orderLine = new ArrayList<>();
}
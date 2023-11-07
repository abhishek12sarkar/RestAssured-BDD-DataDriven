package com.bhn.marketplace.DataTransferObjects.OrderPlacement;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPlacementRequestDTO {
    private String clientProgramNumber;
    private String paymentType;
    private String financialAccountId;
    private EmailContent emailContent;
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    public String getClientProgramNumber() {
        return clientProgramNumber;
    }

    public void setClientProgramNumber(String clientProgramNumber) {
        this.clientProgramNumber = clientProgramNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(String financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public EmailContent getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(EmailContent emailContent) {
        this.emailContent = emailContent;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}


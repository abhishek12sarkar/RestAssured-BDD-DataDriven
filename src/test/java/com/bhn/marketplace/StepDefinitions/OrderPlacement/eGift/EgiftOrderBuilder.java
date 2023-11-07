package com.bhn.marketplace.StepDefinitions.OrderPlacement.eGift;

import com.bhn.marketplace.ApiActions.OrderPlacement.Egift.EgiftOrderPlacementActions;
import com.bhn.marketplace.DataTransferObjects.OrderPlacement.*;
import com.bhn.marketplace.DataTransferObjects.TestDataObjects.EgiftOrderDetails;
import com.bhn.marketplace.DataTransferObjects.TestDataObjects.OrderLine;
import com.bhn.marketplace.Framework.Utils.TestDataManager;
import org.junit.Assert;

public class EgiftOrderBuilder {

    private OrderPlacementRequestDTO orderPlacementRequest = null;
    private OrderDetail orderDetail = null;
    private EmailContent emailContent = null;
    private final EgiftOrderPlacementActions egiftOrderPlacementActions = new EgiftOrderPlacementActions();
    private OrderPlacementResponseDTO eGiftResponse = null;
    private EgiftOrderDetails egiftOrderDetails;

    protected void createOrderRequest() {
        orderPlacementRequest = new OrderPlacementRequestDTO();
    }

    protected void setClientProgramAndPaymentDetails(String country, String paymentType) {
        egiftOrderDetails = TestDataManager.getEgiftOrderDetails(country, paymentType);
        orderPlacementRequest.setClientProgramNumber(egiftOrderDetails.clientProgramNumber);
        orderPlacementRequest.setFinancialAccountId(egiftOrderDetails.faid);
        orderPlacementRequest.setPaymentType(egiftOrderDetails.paymentType);
    }

    protected void setMerchantWithLoadValueAndRecipientDetails() {
        for (OrderLine orderLine : egiftOrderDetails.orderLine) {
            orderDetail = new OrderDetail();
            orderDetail.setContentProvider(orderLine.contentProviderCode);
            orderDetail.setAmount(orderLine.loadValue);
            Recipient recipient = new Recipient();
            recipient.setFirstName(orderLine.recipientFirstName);
            recipient.setLastName(orderLine.recipientLastName);
            recipient.setEmail(orderLine.recipientEmail);
            orderDetail.setRecipient(recipient);
            orderPlacementRequest.getOrderDetails().add(orderDetail);
        }
    }

    protected void setMerchantWithLoadValueAndQuantity() {
        for (OrderLine orderLine : egiftOrderDetails.orderLine) {
            orderDetail = new OrderDetail();
            orderDetail.setContentProvider(orderLine.contentProviderCode);
            orderDetail.setAmount(orderLine.loadValue);
            orderDetail.setQuantity(orderLine.quantity);
            orderPlacementRequest.getOrderDetails().add(orderDetail);
        }
    }

    protected void setEmailClosingName(String emailClosingName) {
        emailContent = new EmailContent();
        emailContent.setEmailClosingName(emailClosingName);
    }

    protected void setUnsubscribeData(String unsubscribeDataMethodType) {
        UnsubscribeData unsubscribeData = new UnsubscribeData();
        unsubscribeData.setMethodType(unsubscribeDataMethodType);
        emailContent.setUnsubscribeData(unsubscribeData);
        orderPlacementRequest.setEmailContent(emailContent);
    }

    protected void placeIndividualEgiftOrder() {
        eGiftResponse = egiftOrderPlacementActions.individualEgiftOrderPlacementDetails(orderPlacementRequest);
    }

    protected void placeBulkEgiftOrder() {
        eGiftResponse = egiftOrderPlacementActions.bulkEgiftOrderPlacementDetails(orderPlacementRequest);
    }

    protected void validateOrderSuccess() {
        Assert.assertTrue("Order placement is not successful\n", eGiftResponse.success);
    }

    protected void validateOrderCompletionPercentage(int expectedPercentage) {
        Assert.assertEquals(expectedPercentage, eGiftResponse.percentComplete);
    }

    protected void validateTransactionIDisNotEmpty() {
        Assert.assertNotNull(eGiftResponse.transactionId);
        Assert.assertFalse(eGiftResponse.transactionId.isEmpty());
    }

    protected void validateOrderNumberIsReceived() {
        Assert.assertNotNull("Order Number is not received", eGiftResponse.orderNumber);
    }
}

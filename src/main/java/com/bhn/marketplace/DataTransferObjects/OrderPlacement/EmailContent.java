package com.bhn.marketplace.DataTransferObjects.OrderPlacement;

public class EmailContent {
    private String emailClosingName;
    private UnsubscribeData unsubscribeData;

    public String getEmailClosingName() {
        return emailClosingName;
    }

    public void setEmailClosingName(String emailClosingName) {
        this.emailClosingName = emailClosingName;
    }

    public UnsubscribeData getUnsubscribeData() {
        return unsubscribeData;
    }

    public void setUnsubscribeData(UnsubscribeData unsubscribeData) {
        this.unsubscribeData = unsubscribeData;
    }
}

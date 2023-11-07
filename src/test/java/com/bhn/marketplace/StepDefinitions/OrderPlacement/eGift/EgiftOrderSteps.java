package com.bhn.marketplace.StepDefinitions.OrderPlacement.eGift;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EgiftOrderSteps extends EgiftOrderBuilder {

    @Given("An WebService user")
    public void an_webservice_user() {
        createOrderRequest();
    }

    @And("User is assigned to a {string} eGift client program with {string} FAID")
    public void user_is_assigned_to_a_client_program_with_faid(String country, String paymentType) {
        setClientProgramAndPaymentDetails(country, paymentType);
    }

    @Given("User provides merchant, load value and recipient details")
    public void user_provides_merchant_loadValue_recipientDetails() {
        setMerchantWithLoadValueAndRecipientDetails();
    }

    @Given("User provides merchant, load value and quantity")
    public void user_provides_merchant_loadValue_quantity() {
        setMerchantWithLoadValueAndQuantity();
    }

    @Given("User sets {string} as email closing name")
    public void user_writes_closing_email(String emailClosingName) {
        setEmailClosingName(emailClosingName);
    }

    @Given("User selects method {string} as unsubscribe data")
    public void user_selects_unsubscribe_data(String unsubscribeDataMethodType) {
        setUnsubscribeData(unsubscribeDataMethodType);
    }

    @When("User places an individual eGift order via submitEgiftIndividual API")
    public void user_places_individual_eGift_order() {
        placeIndividualEgiftOrder();
    }

    @When("User places an bulk eGift order via submitEgiftBulk API")
    public void user_places_bulk_eGift_order() {
        placeBulkEgiftOrder();
    }

    @Then("Order should be successful")
    public void order_should_be_successful() {
        validateOrderSuccess();
    }

    @Then("Order should be {int}% complete")
    public void order_should_be_complete(int expectedPercentage) {
        validateOrderCompletionPercentage(expectedPercentage);
    }

    @Then("Transaction ID should not be empty")
    public void transaction_id_is_not_empty() {
        validateTransactionIDisNotEmpty();
    }

    @Then("Order number is received")
    public void order_number_is_received() {
        validateOrderNumberIsReceived();
    }

}



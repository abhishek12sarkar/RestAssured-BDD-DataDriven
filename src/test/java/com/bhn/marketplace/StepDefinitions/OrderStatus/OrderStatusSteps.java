package com.bhn.marketplace.StepDefinitions.OrderStatus;

import com.bhn.marketplace.ApiActions.OrderInfo.OrderInfoActions;
import com.bhn.marketplace.Framework.API.ApiTestBase;
import com.bhn.marketplace.Framework.API.ResponseValidators;
import com.bhn.marketplace.Framework.Reporting.CucumberReport;
import com.bhn.marketplace.Framework.Utils.TestDataManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class OrderStatusSteps extends ApiTestBase {

    OrderInfoActions orderInfoActions = new OrderInfoActions();
    String orderNumberToRequest = null;
    Response response = null;

    @Given("A valid Order ID")
    public void a_valid_order_id() {
        orderNumberToRequest = TestDataManager.getTestDataInput("VALID_ORDER_NUMBER");
        CucumberReport.logOrderNumber(orderNumberToRequest);
    }

    @Given("An invalid Order ID")
    public void an_invalid_order_id() {
        orderNumberToRequest = TestDataManager.getTestDataInput("INVALID_ORDER_NUMBER");
        CucumberReport.logOrderNumber(orderNumberToRequest);
    }

    @When("Order ID is queried via orderInfo API")
    public void order_id_is_queried_via_orderInfo_api() {
        response = orderInfoActions.getOrderInfoResponse(orderNumberToRequest);
    }

    @Then("Response should be {int}")
    public void response_should_be(int expectedStatusCode) {
        ResponseValidators.validateStatusCode(response, expectedStatusCode);
    }
}
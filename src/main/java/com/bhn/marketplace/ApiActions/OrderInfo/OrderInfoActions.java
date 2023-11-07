package com.bhn.marketplace.ApiActions.OrderInfo;

import com.bhn.marketplace.ApiEndpoints.RewardsOrderProcessing.OrderInfo;
import com.bhn.marketplace.DataTransferObjects.OrderInfo.OrderInfoResponseDTO;
import com.bhn.marketplace.Framework.API.ApiTestBase;
import com.bhn.marketplace.Framework.API.HttpMethods.GetRequest;
import io.restassured.response.Response;

public class OrderInfoActions extends ApiTestBase {

    GetRequest orderInfoRequest = null;

    public OrderInfoActions() {
        orderInfoRequest = new GetRequest();
        orderInfoRequest.request = setDefaultContentType(orderInfoRequest.request);
        orderInfoRequest.request = setDefaultHeaders(orderInfoRequest.request);
    }

    public Response getOrderInfoResponse(String orderNumber) {
        orderInfoRequest.request.queryParam("orderNumber", orderNumber);
        return orderInfoRequest.execute(OrderInfo.OrderInfo_Endpoint);
    }

    public OrderInfoResponseDTO getOrderInfo(String orderNumber) {
        orderInfoRequest.request.queryParam("orderNumber", orderNumber);
        return orderInfoRequest.execute(OrderInfo.OrderInfo_Endpoint, OrderInfoResponseDTO.class);
    }
}

package com.bhn.marketplace.ApiActions.OrderPlacement.Egift;

import com.bhn.marketplace.ApiEndpoints.RewardsOrderProcessing.OrderPlacement;
import com.bhn.marketplace.DataTransferObjects.OrderPlacement.OrderPlacementResponseDTO;
import com.bhn.marketplace.Framework.API.ApiTestBase;
import com.bhn.marketplace.Framework.API.HttpMethods.PostRequest;
import io.restassured.response.Response;

public class EgiftOrderPlacementActions extends ApiTestBase {

    PostRequest egiftOrderRequest = null;

    public EgiftOrderPlacementActions() {
        egiftOrderRequest = new PostRequest();
        egiftOrderRequest.request = setDefaultContentType(egiftOrderRequest.request);
        egiftOrderRequest.request = setDefaultHeaders(egiftOrderRequest.request);
    }

    public Response individualEgiftOrderPlacementDetailsResponse(Object payload) {
        return egiftOrderRequest.execute(OrderPlacement.SubmitEgiftIndividual_Endpoint, payload);
    }

    public <DTO> OrderPlacementResponseDTO individualEgiftOrderPlacementDetails(Object payload) {
        return egiftOrderRequest.execute(OrderPlacement.SubmitEgiftIndividual_Endpoint, payload, OrderPlacementResponseDTO.class);
    }


    public Response bulkEgiftOrderPlacementDetailsResponse(Object payload) {
        return egiftOrderRequest.execute(OrderPlacement.SubmitEgiftBulk_Endpoint, payload);
    }

    public <DTO> OrderPlacementResponseDTO bulkEgiftOrderPlacementDetails(Object payload) {
        return egiftOrderRequest.execute(OrderPlacement.SubmitEgiftBulk_Endpoint, payload, OrderPlacementResponseDTO.class);
    }

}

package com.bhn.marketplace.Framework.API.HttpMethods;

import com.bhn.marketplace.Framework.API.ApiTestBase;
import com.bhn.marketplace.Framework.API.ResponseValidators;
import com.bhn.marketplace.Framework.Authentication.CertificateHandler;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchRequest extends ApiTestBase {

    public RequestSpecification request = null;

    public PatchRequest() {
        this.request = RestAssured.given()
                .spec(logSpec)
                .config(CertificateHandler.getCertificateConfig())
                .baseUri(Api_Url);
    }

    /**
     * Sends a patch request with the specified endpoint and a payload object
     *
     * @return Response object
     */
    public Response execute(String endpoint, Object payload) {
        return request.body(payload).patch(endpoint);
    }

    /**
     * Sends a patch request with the specified endpoint
     *
     * @return Typed response object as per DTO passed.
     */
    public <DTO> DTO execute(String endpoint, Object payload, Class<DTO> dtoClass) {
        Response response = execute(endpoint, payload);
        ResponseValidators.validateSuccessfulResponse(response);
        return response.as(dtoClass);
    }
}

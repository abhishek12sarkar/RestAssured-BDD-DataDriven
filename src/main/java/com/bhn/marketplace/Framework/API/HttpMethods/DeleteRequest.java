package com.bhn.marketplace.Framework.API.HttpMethods;

import com.bhn.marketplace.Framework.API.ApiTestBase;
import com.bhn.marketplace.Framework.API.ResponseValidators;
import com.bhn.marketplace.Framework.Authentication.CertificateHandler;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest extends ApiTestBase {

    public RequestSpecification request = null;

    public DeleteRequest() {
        this.request = RestAssured.given()
                .spec(logSpec)
                .config(CertificateHandler.getCertificateConfig())
                .baseUri(Api_Url);
    }

    /**
     * Sends a delete request with the specified endpoint
     *
     * @return Response object
     */
    public Response execute(String endpoint) {
        return request.delete(endpoint);
    }

    /**
     * Sends a delete request with the specified endpoint
     *
     * @return Typed response object as per DTO passed.
     */
    public <DTO> DTO execute(String endpoint, Class<DTO> dtoClass) {
        Response response = execute(endpoint);
        ResponseValidators.validateSuccessfulResponse(response);
        return response.as(dtoClass);
    }
}

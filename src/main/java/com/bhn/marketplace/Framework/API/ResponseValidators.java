package com.bhn.marketplace.Framework.API;

import com.bhn.marketplace.DataTransferObjects.ErrorResponse.ErrorResponseDTO;
import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseValidators {

    public static void validateSuccessfulResponse(Response response) {
        int statusCode = response.statusCode();
        if (statusCode >= 300) {
            Assert.fail("Response is not successful. \n" + response.asPrettyString());
        }
    }

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        try {
            Assert.assertEquals(expectedStatusCode, response.statusCode());
        } catch (AssertionError statusCodeError) {
            errorHandler(response);
        }
    }

    public static void errorHandler(Response response) {
        try {
            ErrorResponseDTO errorResponse = response.as(ErrorResponseDTO.class);
            System.err.println(errorResponse.getErrors().get(0).errorCode);
            if (errorResponse.getErrors().get(0).message != null) {
                Assert.fail(errorResponse.getErrors().get(0).message);
            }
        } catch (Exception defaultError) {
            Assert.fail("Error Response: \n" + response.getBody().asPrettyString());
        }


    }
}
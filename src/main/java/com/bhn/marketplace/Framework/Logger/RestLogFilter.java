package com.bhn.marketplace.Framework.Logger;

import com.bhn.marketplace.Framework.Reporting.CucumberReport;
import com.bhn.marketplace.Framework.Utils.Parser;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Map;

public class RestLogFilter implements Filter {

    private StringBuilder requestBuilder;

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification,
                           FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {

        Response restResponse = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        requestBuilder = new StringBuilder("Request Details:\n");
        requestBuilder.append("Request method:\t" + filterableRequestSpecification.getMethod() + "\n");
        requestBuilder.append("Request URI:\t" + filterableRequestSpecification.getURI() + "\n");
        requestBuilder.append("Query Params:");
        printParams(filterableRequestSpecification.getQueryParams());
        requestBuilder.append("Path Params:");
        printParams(filterableRequestSpecification.getPathParams());
        printHeaders(filterableRequestSpecification.getHeaders());
        printPayload(filterableRequestSpecification.getBody());
        Log.debug(String.valueOf(requestBuilder));
        CucumberReport.scenario.log(String.valueOf(requestBuilder));

        StringBuilder responseBuilder = new StringBuilder("Response Details:\n");
        responseBuilder.append("Status Code:\t" + restResponse.getStatusCode() + "\n");
        responseBuilder.append("Response Body:\n" + restResponse.getBody().asPrettyString() + "\n");
        Log.debug(String.valueOf(responseBuilder));
        CucumberReport.scenario.log(String.valueOf(responseBuilder));

        return restResponse;
    }

    private void printParams(Map<String, String> map) {
        if (map.isEmpty()) {
            requestBuilder.append("\tNONE\n");
        } else {
            requestBuilder.append("\n");
            map.forEach((key, value) -> requestBuilder.append("\t" + key + " = " + value + "\n"));
        }
    }

    private void printHeaders(Headers headers) {
        requestBuilder.append("Headers:\n");
        headers.asList().forEach((header) -> requestBuilder.append("\t" + header.getName() + " = " + header.getValue() + "\n"));
    }

    private void printPayload(String payload) {
        if (payload == null || payload.isEmpty()) {
            requestBuilder.append("Request Body:\tNone");
        } else {
            requestBuilder.append("Request Body:\n");
            requestBuilder.append(Parser.prettifyJSON(payload));
        }
    }
}

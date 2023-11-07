package com.bhn.marketplace.Framework.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.response.Response;
import org.junit.Assert;

public class Parser {

    public static ObjectMapper jsonWriter = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static <DTO> DTO deserializeJSON(Response response, Class<DTO> dtoClass) {
        return response.as(dtoClass);
    }

    public static String convertObjectToJSON(Object object) {
        try {
            return jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            Assert.fail("Failed to convert object into JSON");
        }
        return null;
    }

    public static String prettifyJSON(String textToJSON) {
        try {
            JsonNode jsonNode = jsonWriter.readTree(textToJSON);
            return jsonWriter.writeValueAsString(jsonNode);
        } catch (JsonProcessingException invalidJSON) {
            Assert.fail("Failed to prettify JSON:\n" + invalidJSON);
        }
        return null;
    }
}

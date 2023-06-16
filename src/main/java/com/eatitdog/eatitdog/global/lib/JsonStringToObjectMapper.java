package com.eatitdog.eatitdog.global.lib;

import com.eatitdog.eatitdog.global.exception.global.ExternalAPIException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringToObjectMapper {

    private JsonStringToObjectMapper() {}

    public static <T> T convert(String original, Class<T> responseClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        T response = null;
        try {
            response = objectMapper.readValue(original, responseClass);
        } catch (Exception e) {
            throw ExternalAPIException.EXCEPTION;
        }
        return response;
    }
}

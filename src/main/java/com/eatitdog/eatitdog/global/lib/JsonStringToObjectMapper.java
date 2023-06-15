package com.eatitdog.eatitdog.global.lib;

import com.eatitdog.eatitdog.domain.product.presentation.dto.api.ProductAPIDto;
import com.eatitdog.eatitdog.global.exception.global.ExternalAPIException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToMapper {

    public static <T> T convert(String original, Class<T> responseClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dto = objectMapper.readValue(response, ProductAPIDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw ExternalAPIException.EXCEPTION;
        }
    }
}

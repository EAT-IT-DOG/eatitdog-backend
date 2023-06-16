package com.eatitdog.eatitdog.domain.product.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ProductNotFoundException();

    private ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 제품을 찾을 수 없습니다.");
    }
}

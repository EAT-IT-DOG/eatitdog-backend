package com.eatitdog.eatitdog.domain.product.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends CustomException {

    public static final CustomException EXCEPTION = new ProductAlreadyExistsException();

    private ProductAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "해당 id를 가진 제품은 이미 DB에 저장되어 있습니다.");
    }
}

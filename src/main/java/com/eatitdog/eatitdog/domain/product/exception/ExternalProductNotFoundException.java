package com.eatitdog.eatitdog.domain.product.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExternalProductNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ExternalProductNotFoundException();

    private ExternalProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 id를 가진 제품을 외부 API에서 찾지 못했습니다.");
    }
}

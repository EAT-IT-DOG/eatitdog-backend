package com.eatitdog.eatitdog.global.exception.global;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidAPIKeyException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidAPIKeyException();

    private InvalidAPIKeyException() {
        super(HttpStatus.UNAUTHORIZED, "API Key 값이 없거나 유효하지 않습니다.");
    }
}

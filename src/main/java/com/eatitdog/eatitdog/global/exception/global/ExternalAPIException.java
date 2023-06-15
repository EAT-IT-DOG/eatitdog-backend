package com.eatitdog.eatitdog.global.exception.global;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExternalAPIException extends CustomException {

    public static final CustomException EXCEPTION = new ExternalAPIException();

    private ExternalAPIException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "외부 서버 API 에러입니다.");
    }
}

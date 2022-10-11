package com.eatitdog.eatitdog.global.exception.global;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CredentialsNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CredentialsNotFoundException();

    private CredentialsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "토큰을 찾지 못했습니다.");
    }
}

package com.eatitdog.eatitdog.global.exception.global;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EncryptException extends CustomException {

    public static final CustomException EXCEPTION = new EncryptException();

    private EncryptException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "암호화 중 오류가 발생했습니다.");
    }
}

package com.eatitdog.eatitdog.domain.auth.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserEmailExistsException extends CustomException {

    public static final CustomException EXCEPTION = new UserEmailExistsException();

    private UserEmailExistsException() {
        super(HttpStatus.BAD_REQUEST, "해당 이메일을 가진 유저가 이미 존재합니다.");
    }
}

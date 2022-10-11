package com.eatitdog.eatitdog.domain.user.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends CustomException {

    public static final CustomException EXCEPTION = new PasswordNotMatchException();

    private PasswordNotMatchException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 옳지 않습니다.");
    }
}

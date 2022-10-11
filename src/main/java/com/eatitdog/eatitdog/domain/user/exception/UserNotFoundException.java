package com.eatitdog.eatitdog.domain.user.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 아이디/이메일을 가진 유저를 찾지 못했습니다.");
    }
}

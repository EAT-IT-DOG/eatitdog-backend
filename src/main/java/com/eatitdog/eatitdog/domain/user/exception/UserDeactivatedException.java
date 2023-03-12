package com.eatitdog.eatitdog.domain.user.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserDeactivatedException extends CustomException {

    public static final CustomException EXCEPTION = new UserDeactivatedException();

    private UserDeactivatedException() {
        super(HttpStatus.FORBIDDEN, "탈퇴했거나 정지된 계정입니다.");
    }
}

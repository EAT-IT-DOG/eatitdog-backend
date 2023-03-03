package com.eatitdog.eatitdog.domain.dog.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DogNotOwnerException extends CustomException {

    public static final CustomException EXCEPTION = new DogNotOwnerException();

    private DogNotOwnerException() {
        super(HttpStatus.FORBIDDEN, "해당 견(강아지)의 견주가 아니므로 권한이 없습니다.");
    }
}

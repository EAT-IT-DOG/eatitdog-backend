package com.eatitdog.eatitdog.domain.dog.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DogNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new DogNotFoundException();

    private DogNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 견(강아지)을 찾을 수 없습니다.");
    }
}

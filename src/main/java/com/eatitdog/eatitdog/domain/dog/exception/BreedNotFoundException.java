package com.eatitdog.eatitdog.domain.dog.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class BreedNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new BreedNotFoundException();

    private BreedNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 견종을 찾을 수 없습니다.");
    }
}

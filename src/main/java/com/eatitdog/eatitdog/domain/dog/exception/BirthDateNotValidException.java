package com.eatitdog.eatitdog.domain.dog.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class BirthDateNotValidException extends CustomException {

    public static final CustomException EXCEPTION = new BirthDateNotValidException();

    private BirthDateNotValidException() {
        super(HttpStatus.BAD_REQUEST, "출생 날짜가 유효하지 않습니다.");
    }
}

package com.eatitdog.eatitdog.domain.apply.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ApplyNotFoundException extends CustomException {

    private ApplyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 신청을 찾을 수 없습니다.");
    }
}

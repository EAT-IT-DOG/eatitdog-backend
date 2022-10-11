package com.eatitdog.eatitdog.domain.food.exception;

import com.eatitdog.eatitdog.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class FoodNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new FoodNotFoundException();

    private FoodNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 음식을 찾을 수 없습니다.");
    }
}

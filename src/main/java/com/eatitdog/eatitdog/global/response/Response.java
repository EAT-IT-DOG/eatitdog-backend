package com.eatitdog.eatitdog.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {

    private final int status;
    private final String message;

    public Response(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}

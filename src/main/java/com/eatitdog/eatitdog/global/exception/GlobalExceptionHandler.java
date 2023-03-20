package com.eatitdog.eatitdog.global.exception;

import com.eatitdog.eatitdog.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return new ResponseEntity<>(
                new Response(status, message),
                status
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new Response(status, e.getMessage()),
                status
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException e) {
        return new ResponseEntity<>(
                new Response(e.getStatus(), e.getMessage()),
                e.getStatus()
        );
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Response> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        e.printStackTrace();
        return new ResponseEntity<>(
                new Response(status, e.getMessage()),
                status
        );
    }
}

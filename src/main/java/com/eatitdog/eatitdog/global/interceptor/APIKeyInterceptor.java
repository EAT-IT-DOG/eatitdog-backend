package com.eatitdog.eatitdog.global.interceptor;

import com.eatitdog.eatitdog.global.exception.global.InvalidAPIKeyException;
import com.eatitdog.eatitdog.global.properties.APIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class APIKeyInterceptor implements HandlerInterceptor {

    private final APIProperties apiProperties;

    public static final String API_KEY_HEADER_NAME = "api-key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")) return true;

        String key = request.getHeader(API_KEY_HEADER_NAME);

//        if (key == null || !key.equals(apiProperties.getKey())) {
//            throw InvalidAPIKeyException.EXCEPTION;
//        }

        return true;
    }
}

package com.eatitdog.eatitdog.global.interceptor;

import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.domain.user.enums.UserStatus;
import com.eatitdog.eatitdog.domain.user.exception.UserDeactivatedException;
import com.eatitdog.eatitdog.global.annotation.AuthenticationCheck;
import com.eatitdog.eatitdog.global.exception.global.CredentialsNotFoundException;
import com.eatitdog.eatitdog.global.lib.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (request.getMethod().equals("OPTIONS")) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthenticationCheck authenticationAnnotation = handlerMethod.getMethodAnnotation(AuthenticationCheck.class);

        if (authenticationAnnotation == null) {
            return true;
        }

        String token = jwt.extract(request, "Bearer");

        if (token == null || token.length() == 0) {
            throw CredentialsNotFoundException.EXCEPTION;
        }

        User user = jwt.validateToken(token);

        if (!user.getStatus().equals(UserStatus.ACTIVE)) {
            throw UserDeactivatedException.EXCEPTION;
        }

        request.setAttribute("user", user.getId());
        return true;
    }
}
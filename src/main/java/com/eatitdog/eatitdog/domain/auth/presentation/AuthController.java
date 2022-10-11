package com.eatitdog.eatitdog.domain.auth.presentation;

import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.LoginRequest;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.eatitdog.eatitdog.domain.auth.service.AuthService;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.CreateUserRequest;
import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.eatitdog.eatitdog.global.annotation.AuthorizationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void join(@RequestBody @Valid CreateUserRequest request) {
        authService.join(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginTokenResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @AuthorizationCheck
    @DeleteMapping("/unregister")
    @ResponseStatus(HttpStatus.OK)
    public void unregister(@RequestAttribute User user) {
        authService.unregister(user);
    }
}

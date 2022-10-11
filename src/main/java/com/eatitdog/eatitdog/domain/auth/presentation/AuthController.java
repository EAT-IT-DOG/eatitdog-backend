package com.eatitdog.eatitdog.domain.auth.presentation;

import com.eatitdog.eatitdog.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.eatitdog.eatitdog.domain.auth.service.AuthService;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public void join(@RequestBody @Valid CreateUserRequest request) {

    }

    @PostMapping("/login")
    public LoginTokenResponse login() {

    }

    @DeleteMapping("/unregister")
    public void unregister() {

    }
}

package com.eatitdog.eatitdog.domain.token.presentation;

import com.eatitdog.eatitdog.domain.token.presentation.dto.request.RefreshAccessTokenRequest;
import com.eatitdog.eatitdog.global.lib.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final JwtProvider jwt;

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public String refreshAccessToken(@RequestBody @Valid RefreshAccessTokenRequest request) {
        return jwt.refresh(request.getRefreshToken());
    }
}

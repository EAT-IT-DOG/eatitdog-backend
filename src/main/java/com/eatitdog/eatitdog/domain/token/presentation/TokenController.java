package com.eatitdog.eatitdog.domain.token.presentation;

import com.eatitdog.eatitdog.global.lib.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final Jwt jwt;

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public String refreshAccessToken(@RequestBody @Valid String refreshToken) {
        return jwt.refresh(refreshToken);
    }
}

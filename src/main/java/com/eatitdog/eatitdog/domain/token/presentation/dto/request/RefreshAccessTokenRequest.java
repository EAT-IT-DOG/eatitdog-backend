package com.eatitdog.eatitdog.domain.token.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.eatitdog.eatitdog.global.statics.ValidMessageConstants.REFRESH_TOKEN_NOT_BLANK;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshAccessTokenRequest {

    @NotBlank(message = REFRESH_TOKEN_NOT_BLANK)
    private String refreshToken;
}

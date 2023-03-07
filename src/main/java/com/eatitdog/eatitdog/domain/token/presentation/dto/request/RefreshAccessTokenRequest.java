package com.eatitdog.eatitdog.domain.token.presentation.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.eatitdog.eatitdog.global.lib.ValidMessageConstants.REFRESH_TOKEN_NOT_BLANK;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshAccessTokenRequest {

    @NotBlank(message = REFRESH_TOKEN_NOT_BLANK)
    private String refreshToken;
}

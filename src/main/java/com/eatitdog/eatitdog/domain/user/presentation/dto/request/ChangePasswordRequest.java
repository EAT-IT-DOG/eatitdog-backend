package com.eatitdog.eatitdog.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.eatitdog.eatitdog.global.statics.ValidMessageConstants.NEW_PASSWORD_NOT_BLANK;
import static com.eatitdog.eatitdog.global.statics.ValidMessageConstants.PREVIOUS_PASSWORD_NOT_BLANK;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangePasswordRequest {

    @NotBlank(message = PREVIOUS_PASSWORD_NOT_BLANK)
    private String previousPassword;

    @NotBlank(message = NEW_PASSWORD_NOT_BLANK)
    private String newPassword;
}

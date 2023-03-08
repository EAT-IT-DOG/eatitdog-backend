package com.eatitdog.eatitdog.domain.user.presentation.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.eatitdog.eatitdog.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateUserRequest {

    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = IMAGE_NOT_BLANK)
    private String image;
}

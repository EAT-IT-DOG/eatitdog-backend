package com.eatitdog.eatitdog.domain.auth.presentation.dto.request;

import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {

    @NotBlank(message = "name은 내용이 있어야 합니다.")
    private String name;

    @NotBlank(message = "name은 내용이 있어야 합니다.")
    private String email;

    @NotBlank(message = "name은 내용이 있어야 합니다.")
    private String password;
}

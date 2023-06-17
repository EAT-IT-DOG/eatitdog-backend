package com.eatitdog.eatitdog.domain.auth.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {

    @NotBlank(message = "name은 내용이 있어야 합니다.")
    private String name;

    @NotBlank(message = "name은 내용이 있어야 합니다.")
    private String email;

    @NotBlank(message = "name은 내용이 있어야 합니다.")
    private String password;
}

package com.eatitdog.eatitdog.domain.dog.presentation.dto.request;

import com.eatitdog.eatitdog.domain.dog.enums.Sex;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import java.time.LocalDate;

import static com.eatitdog.eatitdog.global.lib.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateDogRequest {

    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotNull(message = BIRTH_DATE_NOT_NULL)
    @PastOrPresent(message = BIRTH_DATE_NOT_FUTURE)
    private LocalDate birthDate;

    // TODO : Enum 유효성 검증 커스텀 validation 추가하기
    @NotNull(message = SEX_NOT_NULL)
    private Sex sex;

    @NotBlank(message = BREED_NOT_BLANK)
    private String breed;
}

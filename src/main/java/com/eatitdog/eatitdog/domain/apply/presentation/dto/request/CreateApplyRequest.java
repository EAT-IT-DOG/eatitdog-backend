package com.eatitdog.eatitdog.domain.apply.presentation.dto.request;

import com.eatitdog.eatitdog.domain.apply.domain.Apply;
import com.eatitdog.eatitdog.domain.food.enums.FoodSafeness;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.global.annotation.EnumValid;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.eatitdog.eatitdog.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateApplyRequest {

    @NotBlank(message = FOOD_NAME_NOT_BLANK)
    private String foodName;

    @NotNull(message = FOOD_TYPE_NOT_NULL)
    @EnumValid(enumClass = FoodType.class, message = FOOD_TYPE_ENUM_VALUE)
    private String type;

    @NotNull(message = FOOD_SAFENESS_NOT_NULL)
    @EnumValid(enumClass = FoodType.class, message = FOOD_SAFENESS_ENUM_VALUE)
    private String safeness;

    @NotBlank(message = EATING_METHOD_NOT_BLANK)
    private String eatingMethod;

    @NotBlank(message = SYMPTOM_NOT_BLANK)
    private String symptom;

    @NotBlank(message = BENEFIT_NOT_BLANK)
    private String benefit;

    @NotBlank(message = CAUTION_NOT_BLANK)
    private String caution;

    public Apply toEntity() {
        return Apply.builder()
                .benefit(benefit)
                .caution(caution)
                .eatingMethod(eatingMethod)
                .foodName(foodName)
                .symptom(symptom)
                .safeness(FoodSafeness.valueOf(safeness))
                .type(FoodType.valueOf(type))
                .build();
    }
}

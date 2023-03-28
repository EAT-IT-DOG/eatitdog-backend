package com.eatitdog.eatitdog.domain.food.presentation.dto.response;

import com.eatitdog.eatitdog.domain.food.enums.FoodSafeness;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class FoodNameResponse implements Serializable {

    private String name;
    private FoodSafeness safeness;
}

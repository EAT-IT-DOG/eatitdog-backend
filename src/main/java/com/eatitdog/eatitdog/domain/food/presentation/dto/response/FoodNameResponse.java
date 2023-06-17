package com.eatitdog.eatitdog.domain.food.presentation.dto.response;

import com.eatitdog.eatitdog.domain.food.enums.FoodSafeness;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FoodNameResponse implements Serializable {

    private String name;
    private FoodSafeness safeness;
}

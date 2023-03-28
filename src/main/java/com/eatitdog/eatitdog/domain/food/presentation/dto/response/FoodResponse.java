package com.eatitdog.eatitdog.domain.food.presentation.dto.response;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.enums.FoodSafeness;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FoodResponse implements Serializable {

    private long id;
    private String name;
    private FoodType type;
    private FoodSafeness safeness;
    private String eatingMethod;
    private String symptom;
    private String benefit;
    private String caution;
    private long searchCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDateTime;

    public static FoodResponse entityToResponse(Food food) {
        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .type(food.getType())
                .safeness(food.getSafeness())
                .eatingMethod(food.getEatingMethod())
                .symptom(food.getSymptom())
                .benefit(food.getBenefit())
                .caution(food.getCaution())
                .searchCount(food.getSearchCount())
                .createdDateTime(food.getCreatedDateTime())
                .modifiedDateTime(food.getModifiedDateTime())
                .build();
    }

    public static List<FoodResponse> entityListToResponse(List<Food> foodList) {
        return foodList.stream()
                .map(FoodResponse::entityToResponse)
                .collect(Collectors.toList());
    }


}

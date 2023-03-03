package com.eatitdog.eatitdog.domain.food.presentation;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodNameResponse;
import com.eatitdog.eatitdog.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/type")
    public List<FoodNameResponse> getFoodNameByType(@RequestParam("type") FoodType type) {
        return foodService.getFoodNameByType(type);
    }

    @GetMapping("/name")
    public Food getFoodByName(@RequestParam("name") String name) {
        return foodService.getFoodByName(name);
    }
}

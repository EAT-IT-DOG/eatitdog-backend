package com.eatitdog.eatitdog.domain.food.presentation;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodNameResponse;
import com.eatitdog.eatitdog.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getFoods(
            @RequestParam @PositiveOrZero int page,
            @RequestParam @Positive int size
    ) {
        return foodService.getFoodsByPaging(page, size);
    }

    @GetMapping("/ranking")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodNameResponse> getFoodsBySearchCount() {
        return foodService.getFoodsBySearchCount();
    }

    @GetMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodNameResponse> getFoodNameByType(@RequestParam("type") FoodType type) {
        return foodService.getFoodNameByType(type);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public Food getFoodByName(@RequestParam("name") String name) {
        return foodService.getFoodByName(name);
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public Food getRandomFood() {
        return foodService.getFoodByRandom();
    }
}

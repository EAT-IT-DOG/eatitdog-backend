package com.eatitdog.eatitdog.domain.food.presentation;

import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodNameResponse;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodResponse;
import com.eatitdog.eatitdog.domain.food.service.FoodService;
import io.swagger.annotations.ApiOperation;
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
    public List<FoodResponse> getFoods(
            @RequestParam @PositiveOrZero int page,
            @RequestParam @Positive int size
    ) {
        return foodService.getFoodsByPaging(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "음식 검색", notes = "keyword(음식 이름 포함) 및 type(음식 종류)를 query parameter로 받아서 음식 검색을 처리합니다. keyword, type 둘 다 필수 값이 아니지만, 둘 다 비우게 되면 페이징 없는 GET All API가 되므로 용도에 맞게 사용해주세요.")
    public List<FoodResponse> searchFoods(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam @PositiveOrZero int page,
            @RequestParam @Positive int size
    ) {
        return foodService.getFoodsByKeywordAndType(keyword, type, page, size);
    }

    @GetMapping("/ranking")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodNameResponse> getFoodsBySearchCount() {
        return foodService.getFoodsBySearchCount();
    }

    @GetMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodNameResponse> getFoodNamesByType(@RequestParam("type") FoodType type) {
        return foodService.getFoodNameByType(type);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public FoodResponse getFoodByName(@RequestParam("name") String name) {
        return foodService.getFoodByName(name);
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public FoodResponse getRandomFood() {
        return foodService.getFoodByRandom();
    }
}

package com.eatitdog.eatitdog.domain.food.service;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.domain.food.domain.repository.FoodRepository;
import com.eatitdog.eatitdog.domain.food.exception.FoodNotFoundException;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodNameResponse;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodResponse;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eatitdog.eatitdog.global.statics.CacheConfigKeyConstants.*;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<FoodResponse> getFoodsByPaging(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return FoodResponse.entityListToResponse(foodRepository.findAll(pageRequest).getContent());
    }

    public List<FoodResponse> getFoodsByKeywordAndType(String keyword, String type, int page, int size) {
        List<Food> foodList = foodRepository.findAllByNameContainsOrderBySearchCountDesc(keyword);
        if (StringUtils.hasText(type)) {
            foodList = foodList.stream()
                    .filter(food -> food.getType().equals(FoodType.valueOf(type)))
                    .collect(Collectors.toList());
        }

        // TODO : 페이징 처리 리팩토링하기
        int totalPage = foodList.size() / size;
        if (page > totalPage) {
            return new ArrayList<>();
        }
        int startPageIndex = page * size;
        int endPageIndex = (page + 1) * size - 1;
        if (endPageIndex > foodList.size()) {
            endPageIndex = foodList.size() - 1;
        }

        return FoodResponse.entityListToResponse(foodList.subList(startPageIndex, endPageIndex + 1));
    }

    @Cacheable(value = FOODS_BY_SEARCH_COUNT)
    public List<FoodNameResponse> getFoodsBySearchCount() {
        return foodRepository.findTop18ByOrderBySearchCountDesc()
                .stream()
                .map(food -> new FoodNameResponse(food.getName(), food.getSafeness()))
                .collect(Collectors.toList());
    }

    @Cacheable(value = FOOD_NAMES_BY_TYPE, key = "#type")
    public List<FoodNameResponse> getFoodNameByType(FoodType type) {
        return foodRepository.findAllByType(type)
                .stream()
                .map(food -> new FoodNameResponse(food.getName(), food.getSafeness()))
                .collect(Collectors.toList());
    }

    @Cacheable(value = FOOD_BY_NAME, key = "#name")
    public FoodResponse getFoodByName(String name) {
        Food food = foodRepository.findByName(name)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);
        return FoodResponse.entityToResponse(food);
    }

    @Cacheable(value = FOOD_TYPES)
    public List<String> getFoodTypes() {
        return Stream.of(FoodType.values()).map(Enum::name).collect(Collectors.toList());
    }

    public FoodResponse getFoodByRandom() {
        Food food = foodRepository.findByRandom()
                .get(0);
        return FoodResponse.entityToResponse(food);
    }
}

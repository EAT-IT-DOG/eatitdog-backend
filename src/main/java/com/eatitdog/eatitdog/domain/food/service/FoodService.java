package com.eatitdog.eatitdog.domain.food.service;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import com.eatitdog.eatitdog.domain.food.domain.repository.FoodRepository;
import com.eatitdog.eatitdog.domain.food.exception.FoodNotFoundException;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodNameResponse;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> getFoodsByPaging(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return foodRepository.findAll(pageRequest).getContent();
    }

    public List<Food> getFoodsByKeywordAndType(String keyword, String type, int page, int size) {
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
        return foodList.subList(startPageIndex, endPageIndex + 1);
    }

    public List<FoodNameResponse> getFoodsBySearchCount() {
        return foodRepository.findTop18ByOrderBySearchCountDesc()
                .stream()
                .map(food -> new FoodNameResponse(food.getName(), food.getSafeness()))
                .collect(Collectors.toList());
    }

    public List<FoodNameResponse> getFoodNameByType(FoodType type) {
        return foodRepository.findAllByType(type)
                .stream()
                .map(food -> new FoodNameResponse(food.getName(), food.getSafeness()))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "foodByNameCaching", key = "#name")
    @Transactional(rollbackFor = Exception.class)
    public Food getFoodByName(String name) {
        Food food = foodRepository.findByName(name)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);
        food.increaseCount();
        return foodRepository.save(food);
    }

    public Food getFoodByRandom() {
        return foodRepository.findByRandom()
                .get(0);
    }
}

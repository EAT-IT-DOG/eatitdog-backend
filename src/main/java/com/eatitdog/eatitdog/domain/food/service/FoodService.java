package com.eatitdog.eatitdog.domain.food.service;

import com.eatitdog.eatitdog.domain.food.domain.entity.Food;
import com.eatitdog.eatitdog.domain.food.domain.enums.FoodType;
import com.eatitdog.eatitdog.domain.food.domain.repository.FoodRepository;
import com.eatitdog.eatitdog.domain.food.exception.FoodNotFoundException;
import com.eatitdog.eatitdog.domain.food.presentation.dto.response.FoodNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<FoodNameResponse> getFoodNameByType(FoodType type) {
        return foodRepository.findAllByType(type)
                .stream()
                .map((food) -> new FoodNameResponse(food.getName()))
                .collect(Collectors.toList());
    }

    public Food getFoodByName(String name) {
        return foodRepository.findByName(name)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);
    }
}

package com.eatitdog.eatitdog.domain.food.domain.repository;

import com.eatitdog.eatitdog.domain.food.domain.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}

package com.eatitdog.eatitdog.food.domain.repository;

import com.eatitdog.eatitdog.food.domain.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}

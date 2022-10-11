package com.eatitdog.eatitdog.domain.food.domain.repository;

import com.eatitdog.eatitdog.domain.food.domain.entity.Food;
import com.eatitdog.eatitdog.domain.food.domain.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findAllByType(FoodType type);
    Optional<Food> findByName(String name);
}

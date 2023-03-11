package com.eatitdog.eatitdog.domain.food.domain.repository;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findAllByType(FoodType type);
    List<Food> findTop18ByOrderBySearchCountDesc();
    Optional<Food> findByName(String name);

    @Query(value = "SELECT * FROM eatitdog.food order by RAND() limit 1", nativeQuery = true)
    List<Food> findByRandom();
}

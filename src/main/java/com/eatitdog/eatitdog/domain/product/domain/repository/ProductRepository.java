package com.eatitdog.eatitdog.domain.product.domain.repository;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.product.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @EntityGraph(attributePaths = "food")
    List<Product> findAllByFood(Food food);

    @EntityGraph(attributePaths = "food")
    Optional<Product> findByBarcode(String barcode);
}

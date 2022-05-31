package com.eatitdog.eatitdog.category.domain.repository;

import com.eatitdog.eatitdog.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

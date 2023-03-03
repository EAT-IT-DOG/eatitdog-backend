package com.eatitdog.eatitdog.domain.dog.domain.repository;

import com.eatitdog.eatitdog.domain.dog.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<Breed, Long> {

    Optional<Breed> findByName(String name);
}

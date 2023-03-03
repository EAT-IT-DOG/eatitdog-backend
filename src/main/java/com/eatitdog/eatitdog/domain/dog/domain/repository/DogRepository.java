package com.eatitdog.eatitdog.domain.dog.domain.repository;

import com.eatitdog.eatitdog.domain.dog.domain.Dog;
import com.eatitdog.eatitdog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findAllByUser(User user);
}

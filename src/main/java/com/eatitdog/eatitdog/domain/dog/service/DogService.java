package com.eatitdog.eatitdog.domain.dog.service;

import com.eatitdog.eatitdog.domain.dog.domain.Breed;
import com.eatitdog.eatitdog.domain.dog.domain.Dog;
import com.eatitdog.eatitdog.domain.dog.domain.repository.BreedRepository;
import com.eatitdog.eatitdog.domain.dog.domain.repository.DogRepository;
import com.eatitdog.eatitdog.domain.dog.exception.BreedNotFoundException;
import com.eatitdog.eatitdog.domain.dog.exception.DogNotFoundException;
import com.eatitdog.eatitdog.domain.dog.presentation.dto.request.CreateDogRequest;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;
    private final BreedRepository breedRepository;

    public List<Dog> getDogsWithUser(User user) {
        return dogRepository.findAllByUser(user);
    }

    public List<String> getBreedNameList() {
        return breedRepository.findAll()
                .stream()
                .map(Breed::getName)
                .collect(Collectors.toList());
    }

    public Breed getBreedByName(String breedName) {
        return breedRepository.findByName(breedName)
                .orElseThrow(() -> BreedNotFoundException.EXCEPTION);
    }

    public Dog getDogById(long dogId) {
        return dogRepository.findById(dogId)
                .orElseThrow(() -> DogNotFoundException.EXCEPTION);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createDog(CreateDogRequest request, User user) {
        Dog dog = request.toEntity();
        dog.injectUser(user);

        Breed breed = getBreedByName(request.getName());
        dog.injectBreed(breed);

        dogRepository.save(dog);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDog(long dogId, User user) {
        Dog dog = getDogById(dogId);
        dog.checkOwner(user);
        dogRepository.delete(dog);
    }
}

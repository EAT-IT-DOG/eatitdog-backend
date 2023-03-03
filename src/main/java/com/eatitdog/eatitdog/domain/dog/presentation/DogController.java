package com.eatitdog.eatitdog.domain.dog.presentation;

import com.eatitdog.eatitdog.domain.dog.domain.Dog;
import com.eatitdog.eatitdog.domain.dog.presentation.dto.request.CreateDogRequest;
import com.eatitdog.eatitdog.domain.dog.service.DogService;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.global.annotation.AuthorizationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dog")
public class DogController {

    private final DogService dogService;

    @GetMapping("/my")
    @AuthorizationCheck
    public List<Dog> getMyDogs(
            @RequestAttribute User user
    ) {
        return dogService.getDogsWithUser(user);
    }

    // TODO : 캐싱 필요
    @GetMapping("/breed")
    public List<String> getBreedNameList() {
        return dogService.getBreedNameList();
    }

    @PostMapping
    @AuthorizationCheck
    public void create(
            @RequestBody @Valid CreateDogRequest request,
            @RequestAttribute User user
    ) {
        dogService.createDog(request, user);
    }

    @DeleteMapping
    @AuthorizationCheck
    public void delete(
            @RequestParam long id,
            @RequestAttribute User user
    ) {
        dogService.deleteDog(id, user);
    }
}

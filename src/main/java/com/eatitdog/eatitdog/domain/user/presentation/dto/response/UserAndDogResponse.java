package com.eatitdog.eatitdog.domain.user.presentation.dto.response;

import com.eatitdog.eatitdog.domain.dog.domain.Dog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserAndDogResponse {

    private long id;
    private String name;
    private String email;
    private String image;
    private List<Dog> dog;
}

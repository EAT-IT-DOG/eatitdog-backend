package com.eatitdog.eatitdog.domain.user.presentation.dto.response;

import com.eatitdog.eatitdog.domain.dog.domain.Dog;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@Builder
public class UserAndDogResponse {

    private long id;
    private String name;
    private String email;
    private String image;
    private List<Dog> dog;
}

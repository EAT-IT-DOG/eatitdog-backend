package com.eatitdog.eatitdog.domain.user.service;

import com.eatitdog.eatitdog.domain.dog.domain.Dog;
import com.eatitdog.eatitdog.domain.dog.domain.repository.DogRepository;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.domain.user.domain.repository.UserRepository;
import com.eatitdog.eatitdog.domain.user.presentation.dto.request.ChangePasswordRequest;
import com.eatitdog.eatitdog.domain.user.presentation.dto.request.UpdateUserRequest;
import com.eatitdog.eatitdog.domain.user.presentation.dto.response.UserAndDogResponse;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import com.eatitdog.eatitdog.global.lib.encrypt.Encrypt;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final Encrypt encrypt;

    public UserAndDogResponse getUserInfo(User user) {
        List<Dog> dogList = dogRepository.findAllByUser(user);
        return UserAndDogResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .image(user.getImage())
                .dog(dogList)
                .build();
    }

    public void changePassword(ChangePasswordRequest request, User user) {
        user.updatePassword(encrypt,
                request.getPreviousPassword(),
                request.getNewPassword());
        userRepository.save(user);
    }

    public void updateUserInfo(UpdateUserRequest request, User user) {
        user.updateUser(
                request.getName(),
                request.getEmail(),
                request.getImage());
        userRepository.save(user);
    }
}

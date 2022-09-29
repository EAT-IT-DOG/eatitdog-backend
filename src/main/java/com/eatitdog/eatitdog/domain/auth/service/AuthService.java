package com.eatitdog.eatitdog.domain.auth.service;

import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.CreateUserRequest;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.eatitdog.eatitdog.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void join(CreateUserRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
    }

    public LoginTokenResponse login() {

    }
}

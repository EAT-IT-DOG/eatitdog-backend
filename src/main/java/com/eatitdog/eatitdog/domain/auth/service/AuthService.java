package com.eatitdog.eatitdog.domain.auth.service;

import com.eatitdog.eatitdog.domain.auth.exception.UserEmailExistsException;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.CreateUserRequest;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.eatitdog.eatitdog.domain.user.domain.repository.UserRepository;
import com.eatitdog.eatitdog.global.lib.jwt.Jwt;
import com.eatitdog.eatitdog.global.lib.encrypt.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Encrypt encrypt;
    private final Jwt jwt;

    public void join(CreateUserRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw UserEmailExistsException.EXCEPTION;
        }

        String encryptedPassword = encrypt.encode(request.getPassword());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encryptedPassword)
                .build();

        userRepository.save(user);
    }

    public LoginTokenResponse login() {


    }
}

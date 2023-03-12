package com.eatitdog.eatitdog.domain.auth.service;

import com.eatitdog.eatitdog.domain.auth.exception.UserEmailExistsException;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.CreateUserRequest;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.request.LoginRequest;
import com.eatitdog.eatitdog.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.domain.user.domain.repository.UserRepository;
import com.eatitdog.eatitdog.domain.user.exception.PasswordNotMatchException;
import com.eatitdog.eatitdog.domain.user.exception.UserNotFoundException;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import com.eatitdog.eatitdog.global.lib.jwt.Jwt;
import com.eatitdog.eatitdog.global.lib.encrypt.Encrypt;
import com.eatitdog.eatitdog.global.lib.jwt.JwtType;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Encrypt encrypt;
    private final Jwt jwt;

    @Transactional(rollbackFor = Exception.class)
    public void join(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
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

    @Transactional(rollbackFor = Exception.class)
    public LoginTokenResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!encrypt.match(request.getPassword(), user.getPassword())) {
            throw PasswordNotMatchException.EXCEPTION;
        }

        String accessToken = jwt.createToken(user, JwtType.ACCESS);
        String refreshToken = jwt.createToken(user, JwtType.REFRESH);

        return new LoginTokenResponse(accessToken, refreshToken);
    }

    @Transactional(rollbackFor = Exception.class)
    public void unregister(User user) {
        user.deactivateUser();
        userRepository.save(user);
    }

}

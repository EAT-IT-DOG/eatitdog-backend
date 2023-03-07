package com.eatitdog.eatitdog.domain.user.service;

import com.eatitdog.eatitdog.domain.user.domain.repository.UserRepository;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
}

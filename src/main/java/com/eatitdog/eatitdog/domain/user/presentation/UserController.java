package com.eatitdog.eatitdog.domain.user.presentation;

import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.domain.user.service.UserService;
import com.eatitdog.eatitdog.global.annotation.AuthorizationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/myinfo")
    @AuthorizationCheck
    @ResponseStatus(HttpStatus.OK)
    public User getMyInfo(
            @RequestAttribute User user
    ) {
        return null;
    }

    @PatchMapping("/password")
    @AuthorizationCheck
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(
            @RequestAttribute User user
    ) {

    }

    @PutMapping
    @AuthorizationCheck
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(
            @RequestAttribute User user
    ) {

    }
}

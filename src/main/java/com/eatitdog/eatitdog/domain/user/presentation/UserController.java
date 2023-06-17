package com.eatitdog.eatitdog.domain.user.presentation;

import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.domain.user.presentation.dto.request.ChangePasswordRequest;
import com.eatitdog.eatitdog.domain.user.presentation.dto.request.UpdateUserRequest;
import com.eatitdog.eatitdog.domain.user.presentation.dto.response.UserAndDogResponse;
import com.eatitdog.eatitdog.domain.user.service.UserService;
import com.eatitdog.eatitdog.global.annotation.AuthenticationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/myinfo")
    @AuthenticationCheck
    @ResponseStatus(HttpStatus.OK)
    public UserAndDogResponse getMyInfo(
            @RequestAttribute User user
    ) {
        return userService.getUserInfo(user);
    }

    @PatchMapping("/password")
    @AuthenticationCheck
    @ResponseStatus(HttpStatus.OK)
    public void changeMyPassword(
            @RequestBody @Valid ChangePasswordRequest request,
            @RequestAttribute User user
    ) {
        userService.changePassword(request, user);
    }

    @PutMapping
    @AuthenticationCheck
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(
            @RequestBody @Valid UpdateUserRequest request,
            @RequestAttribute User user
    ) {
        userService.updateUserInfo(request, user);
    }
}

package com.eatitdog.eatitdog.domain.apply.presentation;

import com.eatitdog.eatitdog.domain.apply.presentation.dto.request.CreateApplyRequest;
import com.eatitdog.eatitdog.domain.apply.service.ApplyService;
import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.global.annotation.AuthorizationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    @AuthorizationCheck
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody @Valid CreateApplyRequest request,
            @RequestAttribute User user
    ) {
        applyService.createApply(request, user);
    }
}

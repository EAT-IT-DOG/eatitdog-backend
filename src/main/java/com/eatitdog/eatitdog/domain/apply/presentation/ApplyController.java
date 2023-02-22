package com.eatitdog.eatitdog.domain.apply.presentation;

import com.eatitdog.eatitdog.domain.apply.presentation.dto.request.CreateApplyRequest;
import com.eatitdog.eatitdog.domain.apply.service.ApplyService;
import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    public void create(
            @RequestBody CreateApplyRequest request,
            @RequestAttribute User user
    ) {
        applyService.createApply(request, user);
    }
}

package com.eatitdog.eatitdog.domain.apply.presentation;

import com.eatitdog.eatitdog.domain.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;
}

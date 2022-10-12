package com.eatitdog.eatitdog.domain.apply.service;

import com.eatitdog.eatitdog.domain.apply.domain.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
}

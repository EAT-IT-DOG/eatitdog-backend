package com.eatitdog.eatitdog.domain.apply.service;

import com.eatitdog.eatitdog.domain.apply.domain.entity.Apply;
import com.eatitdog.eatitdog.domain.apply.domain.repository.ApplyRepository;
import com.eatitdog.eatitdog.domain.apply.presentation.dto.request.CreateApplyRequest;
import com.eatitdog.eatitdog.domain.user.domain.entity.User;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createApply(CreateApplyRequest request, User user) {
        Apply apply = request.toEntity();
        apply.injectUser(user);
        applyRepository.save(apply);
    }
}

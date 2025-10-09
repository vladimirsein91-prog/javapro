package ru.vtb.controller.impl;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.controller.LimitConroller;
import ru.vtb.dto.LimitDTO;
import ru.vtb.dto.LimitOperationDto;
import ru.vtb.dto.LimitOperationResponseDto;
import ru.vtb.services.LimitService;

@AllArgsConstructor
@RestController
public class LimitControllerImpl implements LimitConroller {

    private final LimitService limitService;

    @Override
    @Transactional(transactionManager ="transactionManager")
    public LimitDTO getAllTLimits(Long userId) {
        return limitService.getActualLimit(userId);
    }

    @Override
    public LimitDTO getLimitById(Long id) {
        return limitService.getLimitById(id);
    }

    @Override
    @Transactional
    public LimitOperationResponseDto limitOperation(LimitOperationDto limit) {
        return limitService.action(limit.getUserId().longValue(), limit.getTypeOperation(), limit.getAmount());
    }

    @Override
    @Transactional
    public void revoke(String id) {
       limitService.revoke(id);
    }

    @Override
    @Transactional
    public void deleteLimitById(Long id) {
        limitService.deleteLimitById(id);
    }
}

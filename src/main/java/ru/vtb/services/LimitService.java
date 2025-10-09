package ru.vtb.services;

import ru.vtb.dto.LimitDTO;
import ru.vtb.dto.LimitOperationResponseDto;
import ru.vtb.enums.TypeOperation;

import java.math.BigDecimal;

public interface LimitService {
    // Получение актуального лимита
     LimitDTO getActualLimit(Long userId);
    //Обновление лимита
    LimitOperationResponseDto action(Long userId, TypeOperation typeOperation, BigDecimal amount);
    // Откат лимита
    LimitOperationResponseDto revoke(String operationId);
    LimitDTO getLimitById(Long id);

    void deleteLimitById(Long id);
}

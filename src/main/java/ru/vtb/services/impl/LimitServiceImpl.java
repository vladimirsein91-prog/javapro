package ru.vtb.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.dto.LimitDTO;
import ru.vtb.dto.LimitOperationResponseDto;
import ru.vtb.entity.LimitEntity;
import ru.vtb.entity.OperationEntity;
import ru.vtb.enums.StatusEnum;
import ru.vtb.enums.TypeOperation;
import ru.vtb.exception.BadDataException;
import ru.vtb.exception.NotLimitFound;
import ru.vtb.mappers.LimitMapper;
import ru.vtb.repository.LimitRepository;
import ru.vtb.repository.OperationRepository;
import ru.vtb.services.LimitService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final OperationRepository operationRepository;
    private final LimitMapper limitMapper;

    @Override
    public LimitDTO getActualLimit(Long userId) {
        return limitMapper.map(getActual(userId));
    }

    @Override
    public LimitOperationResponseDto action(Long userId, TypeOperation typeOperation, BigDecimal amount) {
        var limit = getActual(userId);
        // СПИСАНИЕ
        if (typeOperation.equals(TypeOperation.DECRIESE))
            if (limit.getAmount().compareTo(amount) > 0) {
                OperationEntity oper = OperationEntity
                        .builder()
                        .typeOperation(typeOperation)
                        .amount(amount)
                        .status(StatusEnum.SUCCESS)
                        .id(UUID.randomUUID())
                        .build();
                limit.setAmount(limit.getAmount().subtract(amount));
                var limitSave = limitRepository.save(limit);
                oper.setLimit(limitSave);
                operationRepository.save(oper);
                return new LimitOperationResponseDto(oper.getId().toString());
            } else {
                throw new BadDataException("enough money today");
            }
        else { // начисление
            if (limit.getAmount().add(amount).compareTo(new BigDecimal("10000")) < 0) {
                OperationEntity oper = OperationEntity
                        .builder()
                        .limit(limit)
                        .id(UUID.randomUUID())
                        .build();
                limit.setAmount(limit.getAmount().add(amount));
                operationRepository.save(oper);
                limitRepository.save(limit);
                return new LimitOperationResponseDto(oper.getId().toString());
            } else {
                throw new BadDataException("enough money");
            }
        }
    }

    @Override
    public LimitOperationResponseDto revoke(String operationId) {
        var oper = operationRepository.findById(UUID.fromString(operationId))
                .filter(s -> s.getStatus().equals(StatusEnum.SUCCESS) && s.getLimit().getOperDate().isEqual(LocalDate.now()))
                .orElseThrow(() -> new BadDataException("operation not found"));
        var limit = getActual(oper.getLimit().getUserId());
        if (oper.getTypeOperation().equals(TypeOperation.DECRIESE)) {
            limit.setAmount(limit.getAmount().add(oper.getAmount()));
        } else {
            limit.setAmount(limit.getAmount().subtract(oper.getAmount()));
        }
        oper.setStatus(StatusEnum.REVOKED);
        limitRepository.save(limit);
        operationRepository.save(oper);
        return new LimitOperationResponseDto(oper.getId().toString());
    }

    @Override
    public LimitDTO getLimitById(Long id) {
        return limitMapper.map(limitRepository.findById(id).orElseThrow(NotLimitFound::new));
    }

    @Override
    public void deleteLimitById(Long id) {
       limitRepository.delete(limitRepository.findById(id).orElseThrow(NotLimitFound::new));
    }


    private LimitEntity getActual(Long userId) {
        // получаем сисок лимитов c пессимистичной блокировкой юзера
        var limits = limitRepository.getLimitEntityByUserId(userId);
        return limits.stream()
                .filter(l -> l.getOperDate().isEqual(LocalDate.now()))
                .findFirst()
                .orElse(LimitEntity.builder()
                        .amount(new BigDecimal(10000))
                        .operDate(LocalDate.now())
                        .userId(userId)
                        .build());
    }

    // создание лимита за новый день в 10000
    private LimitEntity createNewLimit(Long userId) {
        try {
            return limitRepository.save(
                    LimitEntity.builder()
                            .userId(userId)
                            .amount(new BigDecimal(10000))
                            .operDate(LocalDate.now())
                            .build());
        } catch (DataIntegrityViolationException e) {
            log.warn("лимит существует");
            return getActual(userId);
        }
    }
}

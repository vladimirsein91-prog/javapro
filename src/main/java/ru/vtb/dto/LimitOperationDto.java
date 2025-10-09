package ru.vtb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.vtb.enums.TypeOperation;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LimitOperationDto {
    @NotBlank
    BigDecimal amount;
    @NotBlank
    TypeOperation typeOperation;
    @NotBlank
    Integer userId;
}

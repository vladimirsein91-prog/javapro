package ru.vtb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitDTO {
    private Long userId;
    private BigDecimal amount;
    private LocalDate operDate;
    private String errorMessage;
    private String errorCode;
}

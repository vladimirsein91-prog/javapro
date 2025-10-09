package ru.vtb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LimitOperationResponseDto {
    @NotBlank
    String operationId;
}

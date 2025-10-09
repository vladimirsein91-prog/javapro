package ru.vtb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.vtb.dto.LimitDTO;
import ru.vtb.dto.LimitOperationDto;
import ru.vtb.dto.LimitOperationResponseDto;

import java.util.List;


@Tag(name = "limits", description = "Управление лимитами")
@RequestMapping("/limits")
public interface LimitConroller {


  @Operation(
          summary = "Получить текущий лимит на текущий день",
          description = "Возвращает лимит на текущий день",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Лимит успешно получен"
                  )
          }
  )
  @GetMapping("/actual")
  LimitDTO getAllTLimits(@RequestParam(value = "userId", required = true) Long userId);

  @Operation(
          summary = "Получить лимит по ID",
          description = "Находит лимит по её уникальному ID",
          responses = {
                  @ApiResponse(
                          responseCode = "200", description = "лимит найден"
                  ),
                  @ApiResponse(
                          responseCode = "404", description = "лимит не найден"
                  )
          },
          parameters = @Parameter(name = "id", description = "ID лимита", example = "1")
  )
  @GetMapping("/{id}")
  LimitDTO getLimitById(@PathVariable Long id);

  @Operation(
          summary = "Изменение баланса лимита",
          description = "Обновляет баланс лимита",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Тело запроса с описанием баланса лимита",
                  required = true,
                  content = @Content(
                          examples = @ExampleObject(
                                  name = "Пример задачи",
                                  value = "{ \"typeOperation\": \"INCREASE\", \"amount\": \"100\", \"operDay\":\"2025-01-01\" }"
                          )
                  )
          )
  )
  @PostMapping
  LimitOperationResponseDto limitOperation(@RequestBody @Valid LimitOperationDto limit);

  @Operation(
          summary = "Откатить операцию по лимиту",
          description = "Откатывает ранее выполненую операцию по лимиту",
          parameters = {
                  @Parameter(name = "id", description = "ID операции", example = "ABCx-111")
          }
  )
  @PutMapping("/revoke-operation/{id}")
  void revoke(@PathVariable String id);

  @Operation(
          summary = "Удалить лимит",
          description = "Удаляет лимит с указанным ID",
          parameters = @Parameter(name = "id", description = "ID лимта", example = "1")
  )
  @DeleteMapping("/{id}")
  void deleteLimitById(@PathVariable Long id);
}

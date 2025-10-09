package ru.vtb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vtb.dto.LimitDTO;
import ru.vtb.exception.BadDataException;
import ru.vtb.exception.NotLimitFound;

@ControllerAdvice
public class LimitControllerAdvice {

    @ExceptionHandler(NotLimitFound.class)
    public final ResponseEntity<LimitDTO> handleNotFoundException(NotLimitFound ex) {
        LimitDTO limitDTO = new LimitDTO();
        limitDTO.setErrorMessage("лимит не найден");
        limitDTO.setErrorCode("404");
        return new ResponseEntity<>(limitDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadDataException.class)
    public final ResponseEntity<LimitDTO> handleBadDatadException(BadDataException ex) {
        LimitDTO limitDTO = new LimitDTO();
        limitDTO.setErrorMessage(ex.getMessage());
        limitDTO.setErrorCode("422");
        return new ResponseEntity<>(limitDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

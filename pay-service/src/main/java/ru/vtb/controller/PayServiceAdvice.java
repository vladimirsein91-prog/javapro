package ru.vtb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.vtb.exception.RequestException;
import ru.vtb.pojo.PayResult;

import java.net.ConnectException;

@RestControllerAdvice
public class PayServiceAdvice {
    @ExceptionHandler(RequestException.class)
    public final ResponseEntity<PayResult> handleNotFoundException(RequestException ex) {
        return new ResponseEntity<>(new PayResult(ex.getMessage(),"500"), HttpStatus.INTERNAL_SERVER_ERROR);
}

 @ExceptionHandler(java.net.ConnectException.class)
 public final ResponseEntity<PayResult> handleNotConnect(ConnectException ex) {
     return new ResponseEntity<>(new PayResult(ex.getMessage(), "500"), HttpStatus.INTERNAL_SERVER_ERROR);
 }
}

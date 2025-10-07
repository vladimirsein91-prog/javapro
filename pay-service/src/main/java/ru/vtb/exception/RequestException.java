package ru.vtb.exception;

import lombok.Data;

@Data
public class RequestException extends RuntimeException{
    private String message;
    public RequestException(String message){
        this.message = message;
    }
}

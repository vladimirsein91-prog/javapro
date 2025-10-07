package ru.vtb.exception;

import lombok.Getter;

@Getter
public class BadTestClassError extends RuntimeException {
    private String myMessage;
    public BadTestClassError(String message){
        this.myMessage = message;
    }
}

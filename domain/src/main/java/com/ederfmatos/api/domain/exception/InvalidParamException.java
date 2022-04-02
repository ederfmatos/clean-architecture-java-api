package com.ederfmatos.api.domain.exception;

public class InvalidParamException extends DomainException {

    public InvalidParamException(String paramName) {
        super("The param " + paramName + " is invalid");
    }
}

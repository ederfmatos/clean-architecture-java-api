package com.ederfmatos.api.domain.exception;

public class RequiredFieldException extends DomainException {

    public RequiredFieldException(String fieldName) {
        super("The field " + fieldName + " is required");
    }
}

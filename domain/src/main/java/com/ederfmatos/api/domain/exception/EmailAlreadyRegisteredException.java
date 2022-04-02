package com.ederfmatos.api.domain.exception;

public class EmailAlreadyRegisteredException extends DomainException {
    public EmailAlreadyRegisteredException() {
        super("This email already registered");
    }
}

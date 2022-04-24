package com.ederfmatos.api.domain.entities;

import com.ederfmatos.api.domain.exception.InvalidParamException;

public class Email {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String value;

    public Email(String value) {
        if (this.isInvalid(value)) {
            throw new InvalidParamException("email");
        }
        this.value = value;
    }

    private boolean isInvalid(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return !value.matches(EMAIL_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

}

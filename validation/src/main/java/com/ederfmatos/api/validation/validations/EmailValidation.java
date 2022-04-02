package com.ederfmatos.api.validation.validations;

import com.ederfmatos.api.domain.exception.DomainException;
import com.ederfmatos.api.domain.exception.InvalidParamException;

public final class EmailValidation extends FieldValidation {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String fieldName;

    public EmailValidation(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public DomainException validate(Object object) {
        Object value = getFieldValue(object, this.fieldName);

        if (value == null || value.toString().isBlank()) {
            return new InvalidParamException(this.fieldName);
        }

        if (value.toString().matches(EMAIL_REGEX)) {
            return null;
        }

        return new InvalidParamException(this.fieldName);
    }

}

package com.ederfmatos.api.validation.validations;

import com.ederfmatos.api.domain.exception.RequiredFieldException;

public final class RequiredFieldValidation extends FieldValidation {

    private final String fieldName;

    public RequiredFieldValidation(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void validate(Object object) {
        Object value = getFieldValue(object, this.fieldName);

        if (value == null || value.toString().isBlank()) {
            throw new RequiredFieldException(this.fieldName);
        }
    }

}

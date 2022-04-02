package com.ederfmatos.api.validation.validations;

import com.ederfmatos.api.domain.exception.DomainException;
import com.ederfmatos.api.domain.exception.InvalidParamException;

import java.util.Objects;

public final class CompareFieldsValidation extends FieldValidation {

    private final String fieldName;
    private final String fieldToCompareName;

    public CompareFieldsValidation(String fieldName, String fieldToCompareName) {
        this.fieldName = fieldName;
        this.fieldToCompareName = fieldToCompareName;
    }

    @Override
    public DomainException validate(Object object) {
        Object value = getFieldValue(object, this.fieldName);
        Object valueToCompare = getFieldValue(object, this.fieldToCompareName);

        if (Objects.equals(value, valueToCompare)) {
            return null;
        }

        return new InvalidParamException(this.fieldToCompareName);
    }

}

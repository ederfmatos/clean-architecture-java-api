package com.ederfmatos.api.validation.validations;

import com.ederfmatos.api.presentation.protocol.Validation;

import java.lang.reflect.Field;

abstract class FieldValidation implements Validation {

    protected Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.trySetAccessible();

            return field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}

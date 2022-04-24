package com.ederfmatos.api.validation.validations;

import com.ederfmatos.api.presentation.protocol.Validation;

import java.util.Arrays;
import java.util.List;

public class ValidationComposite implements Validation {

    private final List<Validation> validations;

    public ValidationComposite(Validation... validations) {
        this.validations = Arrays.asList(validations);
    }

    @Override
    public void validate(Object input) {
        validations.forEach(validation -> validation.validate(input));
    }

}

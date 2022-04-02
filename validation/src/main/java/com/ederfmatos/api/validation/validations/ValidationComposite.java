package com.ederfmatos.api.validation.validations;

import com.ederfmatos.api.domain.exception.DomainException;
import com.ederfmatos.api.presentation.protocol.Validation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidationComposite implements Validation {

    private final List<Validation> validations;

    public ValidationComposite(Validation... validations) {
        this.validations = Arrays.asList(validations);
    }

    @Override
    public DomainException validate(Object input) {
        return validations.stream()
                .map(validation -> validation.validate(input))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

}

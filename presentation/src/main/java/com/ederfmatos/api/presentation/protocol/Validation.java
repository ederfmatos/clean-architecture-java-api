package com.ederfmatos.api.presentation.protocol;

import com.ederfmatos.api.domain.exception.DomainException;

public interface Validation {

    void validate(Object request) throws DomainException;

}

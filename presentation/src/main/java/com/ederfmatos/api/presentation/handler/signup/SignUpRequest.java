package com.ederfmatos.api.presentation.handler.signup;

import com.ederfmatos.api.domain.entities.Email;

public record SignUpRequest(
        String name,
        Email email,
        String password,
        String passwordConfirmation
) {
}

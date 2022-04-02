package com.ederfmatos.api.presentation.handler.signup;

public record SignUpRequest(
        String name,
        String email,
        String password,
        String passwordConfirmation
) {
}

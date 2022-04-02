package com.ederfmatos.api.main.validation;

import com.ederfmatos.api.validation.validations.CompareFieldsValidation;
import com.ederfmatos.api.validation.validations.EmailValidation;
import com.ederfmatos.api.validation.validations.RequiredFieldValidation;
import com.ederfmatos.api.validation.validations.ValidationComposite;

public final class SignUpValidation extends ValidationComposite {

    public SignUpValidation() {
        super(
                new RequiredFieldValidation("name"),
                new RequiredFieldValidation("email"),
                new RequiredFieldValidation("password"),
                new RequiredFieldValidation("passwordConfirmation"),
                new CompareFieldsValidation("password", "passwordConfirmation"),
                new EmailValidation("email")
        );
    }
}
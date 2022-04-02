package com.ederfmatos.api.main.factory.handler.signup;

import com.ederfmatos.api.domain.usecases.account.add.AddAccountUseCase;
import com.ederfmatos.api.main.validation.SignUpValidation;
import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.protocol.Validation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpHandlerFactory {

    @Bean
    public SignUpHandler signUpHandler(AddAccountUseCase addAccountUseCase) {
        Validation validation = new SignUpValidation();
        return new SignUpHandler(validation, addAccountUseCase);
    }

}

package com.ederfmatos.spring.api.config;

import com.ederfmatos.api.data.protocol.criptography.Hasher;
import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.data.usecases.DbAddAccountUseCase;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountUseCase;
import com.ederfmatos.api.infra.criptography.MessageDigestHasher;
import com.ederfmatos.api.infra.database.mongodb.DatabaseFactory;
import com.ederfmatos.api.infra.database.mongodb.account.AccountRepository;
import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.protocol.Validation;
import com.ederfmatos.api.validation.validations.CompareFieldsValidation;
import com.ederfmatos.api.validation.validations.RequiredFieldValidation;
import com.ederfmatos.api.validation.validations.ValidationComposite;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpFactory {

    private static final DatabaseFactory DATABASE_FACTORY = new DatabaseFactory("mongodb://localhost:27017");

    @Bean
    public SignUpHandler createSignUpHandler() {
        return new SignUpHandler(validation(), addAccountUseCase());
    }

    private Validation validation() {
        return new ValidationComposite(
                new RequiredFieldValidation("name"),
                new RequiredFieldValidation("email"),
                new RequiredFieldValidation("password"),
                new RequiredFieldValidation("passwordConfirmation"),
                new CompareFieldsValidation("password", "passwordConfirmation")
        );
    }

    private Hasher hasher() {
        try {
            return new MessageDigestHasher();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private LoadAccountByEmailRepository loadAccountByEmailRepository() {
        return DATABASE_FACTORY.createLoadAccountByEmailRepository();
    }

    private AddAccountRepository addAccountRepository() {
        return DATABASE_FACTORY.createAddAccountRepository();
    }

    private AddAccountUseCase addAccountUseCase() {
        return new DbAddAccountUseCase(addAccountRepository(), hasher(), loadAccountByEmailRepository());
    }

}

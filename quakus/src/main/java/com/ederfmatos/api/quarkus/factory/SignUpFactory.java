package com.ederfmatos.api.quarkus.factory;

import com.ederfmatos.api.data.protocol.criptography.Hasher;
import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.data.usecases.DbAddAccountUseCase;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountUseCase;
import com.ederfmatos.api.infra.criptography.MessageDigestHasher;
import com.ederfmatos.api.infra.database.mongodb.account.AccountRepository;
import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.protocol.Validation;
import com.ederfmatos.api.validation.validations.CompareFieldsValidation;
import com.ederfmatos.api.validation.validations.RequiredFieldValidation;
import com.ederfmatos.api.validation.validations.ValidationComposite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@ApplicationScoped
public class SignUpFactory {

    @Produces
    @ApplicationScoped
    public SignUpHandler createSignUpHandler() {
        return new SignUpHandler(validation(), addAccountUseCase());
    }

    @Produces
    @ApplicationScoped
    private Validation validation() {
        return new ValidationComposite(
                new RequiredFieldValidation("name"),
                new RequiredFieldValidation("email"),
                new RequiredFieldValidation("password"),
                new RequiredFieldValidation("passwordConfirmation"),
                new CompareFieldsValidation("password", "passwordConfirmation")
        );
    }

    @Produces
    @ApplicationScoped
    public Hasher hasher() {
        try {
            return new MessageDigestHasher();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Produces
    @ApplicationScoped
    public AddAccountRepository addAccountRepository() {
        return new AccountRepository();
    }

    @Produces
    @ApplicationScoped
    public LoadAccountByEmailRepository loadAccountByEmailRepository() {
        return new AccountRepository();
    }

    @Inject
    @Produces
    @ApplicationScoped
    public AddAccountUseCase addAccountUseCase() {
        return new DbAddAccountUseCase(addAccountRepository(), hasher(), loadAccountByEmailRepository());
    }

}

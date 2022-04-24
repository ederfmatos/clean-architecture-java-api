package com.ederfmatos.api.presentation.handler.signup;

import com.ederfmatos.api.domain.model.account.Account;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountModel;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountUseCase;
import com.ederfmatos.api.presentation.protocol.Handler;
import com.ederfmatos.api.presentation.protocol.Validation;

public final class SignUpHandler implements Handler<SignUpRequest, SignUpResponse> {

    private final Validation validation;
    private final AddAccountUseCase addAccountUseCase;

    public SignUpHandler(Validation validation, AddAccountUseCase addAccountUseCase) {
        this.validation = validation;
        this.addAccountUseCase = addAccountUseCase;
    }

    @Override
    public SignUpResponse handle(SignUpRequest signUpRequest) {
        this.validation.validate(signUpRequest);

        AddAccountModel addAccountModel = new AddAccountModel(
                signUpRequest.name(),
                signUpRequest.email(),
                signUpRequest.password()
        );

        Account account = this.addAccountUseCase.add(addAccountModel);

        return new SignUpResponse(
                account.id(),
                account.name(),
                account.email().toString(),
                account.accessToken()
        );
    }

}

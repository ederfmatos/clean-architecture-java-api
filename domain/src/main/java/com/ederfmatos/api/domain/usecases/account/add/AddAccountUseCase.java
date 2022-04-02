package com.ederfmatos.api.domain.usecases.account.add;

import com.ederfmatos.api.domain.model.account.Account;

public interface AddAccountUseCase {

    Account add(AddAccountModel addAccountModel);

}

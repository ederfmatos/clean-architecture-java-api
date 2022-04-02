package com.ederfmatos.api.data.protocol.database.account;

import com.ederfmatos.api.domain.model.account.Account;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountModel;

public interface AddAccountRepository {

    Account add(AddAccountModel addAccountModel);

}

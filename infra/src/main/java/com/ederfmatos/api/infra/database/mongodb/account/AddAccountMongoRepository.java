package com.ederfmatos.api.infra.database.mongodb.account;

import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.domain.model.account.Account;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountModel;
import com.ederfmatos.api.infra.database.mongodb.account.model.AccountMongoEntity;

import java.util.UUID;

public final class AddAccountMongoRepository implements AddAccountRepository {

    private final AccountMongoRepository accountRepository;

    public AddAccountMongoRepository(AccountMongoRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account add(AddAccountModel addAccountModel) {
        AccountMongoEntity accountMongoEntity = new AccountMongoEntity(
                UUID.randomUUID().toString(),
                addAccountModel.name(),
                addAccountModel.email(),
                addAccountModel.password(),
                null
        );
        return accountRepository.save(accountMongoEntity);
    }

}

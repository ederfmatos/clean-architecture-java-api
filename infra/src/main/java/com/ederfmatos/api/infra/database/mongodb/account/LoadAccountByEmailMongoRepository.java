package com.ederfmatos.api.infra.database.mongodb.account;

import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.domain.model.account.Account;

public final class LoadAccountByEmailMongoRepository implements LoadAccountByEmailRepository {

    private final AccountMongoRepository accountRepository;

    public LoadAccountByEmailMongoRepository(AccountMongoRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account loadByEmail(String email) {
        return this.accountRepository.findByEmail(email);
    }

}

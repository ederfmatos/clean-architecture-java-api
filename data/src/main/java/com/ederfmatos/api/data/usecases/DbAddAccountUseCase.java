package com.ederfmatos.api.data.usecases;

import com.ederfmatos.api.data.protocol.criptography.Hasher;
import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.domain.exception.EmailAlreadyRegisteredException;
import com.ederfmatos.api.domain.model.account.Account;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountModel;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountUseCase;

import java.util.Objects;

public final class DbAddAccountUseCase implements AddAccountUseCase {

    private final AddAccountRepository addAccountRepository;
    private final Hasher hasher;
    private final LoadAccountByEmailRepository loadAccountByEmailRepository;

    public DbAddAccountUseCase(
            AddAccountRepository addAccountRepository,
            Hasher hasher,
            LoadAccountByEmailRepository loadAccountByEmailRepository
    ) {
        this.addAccountRepository = addAccountRepository;
        this.hasher = hasher;
        this.loadAccountByEmailRepository = loadAccountByEmailRepository;
    }

    @Override
    public Account add(AddAccountModel addAccountModel) {
        Account accountWithSameEmail = this.loadAccountByEmailRepository.loadByEmail(addAccountModel.email());
        if (Objects.nonNull(accountWithSameEmail)) {
            throw new EmailAlreadyRegisteredException();
        }

        String hashedPassword = this.hasher.hash(addAccountModel.password());

        AddAccountModel accountModel = new AddAccountModel(
                addAccountModel.name(),
                addAccountModel.email(),
                hashedPassword
        );
        return this.addAccountRepository.add(accountModel);
    }

}

package com.ederfmatos.api.main.factory.repository;

import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.infra.database.mongodb.account.AccountMongoRepository;
import com.ederfmatos.api.infra.database.mongodb.account.AddAccountMongoRepository;
import com.ederfmatos.api.infra.database.mongodb.account.LoadAccountByEmailMongoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRepositoryFactory {

    private final AccountMongoRepository accountMongoRepository;

    public AccountRepositoryFactory(AccountMongoRepository accountMongoRepository) {
        this.accountMongoRepository = accountMongoRepository;
    }

    @Bean
    public LoadAccountByEmailRepository loadAccountByEmailRepository() {
        return new LoadAccountByEmailMongoRepository(accountMongoRepository);
    }

    @Bean
    public AddAccountRepository addAccountRepository() {
        return new AddAccountMongoRepository(accountMongoRepository);
    }

}

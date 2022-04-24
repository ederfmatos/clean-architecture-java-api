package com.ederfmatos.api.infra.database.mongodb;

import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.infra.database.mongodb.account.AccountRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseFactory {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final AccountRepository accountRepository;

    public DatabaseFactory(String mongoConnectionUrl) {
        this.mongoClient = MongoClients.create(mongoConnectionUrl);
        this.mongoDatabase = this.mongoClient.getDatabase("clean-java-api");

        this.accountRepository = new AccountRepository(mongoDatabase);
    }

    public LoadAccountByEmailRepository createLoadAccountByEmailRepository() {
        return accountRepository;
    }

    public AddAccountRepository createAddAccountRepository() {
        return accountRepository;
    }

}

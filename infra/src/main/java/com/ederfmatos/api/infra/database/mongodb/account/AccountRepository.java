package com.ederfmatos.api.infra.database.mongodb.account;

import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.domain.entities.Email;
import com.ederfmatos.api.domain.model.account.Account;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountModel;
import com.ederfmatos.api.infra.database.mongodb.account.model.AccountMongoEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.UUID;

public class AccountRepository implements LoadAccountByEmailRepository, AddAccountRepository {

    private final MongoCollection<Document> accountsCollection;

    public AccountRepository(MongoDatabase mongoDatabase) {
        this.accountsCollection = mongoDatabase.getCollection("accounts");
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

        Document document = mapAccountToDocument(accountMongoEntity);
        this.accountsCollection.insertOne(document);

        return accountMongoEntity;
    }


    @Override
    public Account loadByEmail(Email email) {
        Document document = accountsCollection.find(new BasicDBObject("email", email.toString())).first();
        if (document == null) {
            return null;
        }

        return mapDocumentToAccount(document);
    }

    private Account mapDocumentToAccount(Document document) {
        return new AccountMongoEntity(
                document.getString("_id"),
                document.getString("name"),
                new Email(document.getString("email")),
                document.getString("password"),
                document.getString("accessToken")
        );
    }

    private Document mapAccountToDocument(AccountMongoEntity account) {
        return new Document()
                .append("_id", account.id())
                .append("name", account.name())
                .append("email", account.email().toString())
                .append("password", account.password())
                .append("accessToken", account.accessToken())
                ;
    }
}

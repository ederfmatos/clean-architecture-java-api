package com.ederfmatos.api.infra.database.mongodb.account.model;

import com.ederfmatos.api.domain.entities.Email;
import com.ederfmatos.api.domain.model.account.Account;

public record AccountMongoEntity(
        String id,
        String name,
        Email email,
        String password,
        String accessToken
) implements Account {

}

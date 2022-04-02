package com.ederfmatos.api.infra.database.mongodb.account.model;

import com.ederfmatos.api.domain.model.account.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("accounts")
public record AccountMongoEntity(
        @Id
        String id,
        String name,
        String email,
        String password,
        String accessToken
) implements Account {

}

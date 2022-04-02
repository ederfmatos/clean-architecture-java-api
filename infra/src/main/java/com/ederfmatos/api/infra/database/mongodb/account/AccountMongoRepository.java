package com.ederfmatos.api.infra.database.mongodb.account;

import com.ederfmatos.api.infra.database.mongodb.account.model.AccountMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMongoRepository extends MongoRepository<AccountMongoEntity, String> {

    AccountMongoEntity findByEmail(String email);

}

package com.ederfmatos.api.data.protocol.database.account;

import com.ederfmatos.api.domain.entities.Email;
import com.ederfmatos.api.domain.model.account.Account;

public interface LoadAccountByEmailRepository {

    Account loadByEmail(Email email);

}

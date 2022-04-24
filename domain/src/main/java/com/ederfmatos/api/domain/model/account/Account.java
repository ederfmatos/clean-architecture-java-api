package com.ederfmatos.api.domain.model.account;

import com.ederfmatos.api.domain.entities.Email;

public interface Account {

    String id();

    String name();

    Email email();

    String password();

    String accessToken();

}

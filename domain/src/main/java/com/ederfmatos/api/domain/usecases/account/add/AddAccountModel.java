package com.ederfmatos.api.domain.usecases.account.add;

import com.ederfmatos.api.domain.entities.Email;

public record AddAccountModel(
        String name,
        Email email,
        String password
) {

}

package com.ederfmatos.api.domain.usecases.account.add;

public record AddAccountModel(
        String name,
        String email,
        String password
) {

}

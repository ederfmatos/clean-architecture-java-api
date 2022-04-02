package com.ederfmatos.api.main.factory.usecase;

import com.ederfmatos.api.data.protocol.criptography.Hasher;
import com.ederfmatos.api.data.protocol.database.account.AddAccountRepository;
import com.ederfmatos.api.data.protocol.database.account.LoadAccountByEmailRepository;
import com.ederfmatos.api.data.usecases.DbAddAccountUseCase;
import com.ederfmatos.api.domain.usecases.account.add.AddAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddAccountUseCaseFactory {

    @Bean
    public AddAccountUseCase addAccountUseCase(Hasher hasher, AddAccountRepository addAccountRepository, LoadAccountByEmailRepository loadAccountByEmailRepository) {
        return new DbAddAccountUseCase(addAccountRepository, hasher, loadAccountByEmailRepository);
    }

}

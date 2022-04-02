package com.ederfmatos.api.main.factory.criptography;

import com.ederfmatos.api.data.protocol.criptography.Hasher;
import com.ederfmatos.api.infra.criptography.MessageDigestHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;

@Configuration
public class HasherFactory {

    @Bean
    public Hasher hasher() throws NoSuchAlgorithmException {
        return new MessageDigestHasher();
    }

}

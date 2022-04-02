package com.ederfmatos.api.infra.criptography;

import com.ederfmatos.api.data.protocol.criptography.Hasher;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestHasher implements Hasher {

    private final MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

    public MessageDigestHasher() throws NoSuchAlgorithmException {
    }

    @Override
    public String hash(String value) {
        byte[] messageDigest = algorithm.digest(value.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

}

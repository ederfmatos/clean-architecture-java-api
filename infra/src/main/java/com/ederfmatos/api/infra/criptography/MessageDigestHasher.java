package com.ederfmatos.api.infra.criptography;

import com.ederfmatos.api.data.protocol.criptography.Hasher;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class MessageDigestHasher implements Hasher {

    private final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    private final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");

    public MessageDigestHasher() throws NoSuchAlgorithmException, NoSuchProviderException {
    }

    @Override
    public String hash(String value) {
        byte[] salt = createSalt();
        messageDigest.update(salt);

        byte[] bytes = messageDigest.digest(value.getBytes());

        return convertToHex(bytes);
    }

    private byte[] createSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

}

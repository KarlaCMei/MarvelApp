package com.example.marvelapp.retrofit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MarvelAPIHashGenerator {
    public static String generateHash(String timestamp, String privateKey, String publicKey) {
        try {
            String input = timestamp + privateKey + publicKey;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

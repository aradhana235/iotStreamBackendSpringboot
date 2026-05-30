package com.alphacore;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) {

        var key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String secret = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("cjv4Zi7WdK2H4hBfCiqQQvzrFOOccS70Rv1f0mjo96o=");
        System.out.println(secret);
    }
}
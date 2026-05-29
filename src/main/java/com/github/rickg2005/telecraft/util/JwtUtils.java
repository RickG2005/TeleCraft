package com.github.rickg2005.telecraft.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET_STRING = "Rick'sSuperSecretTeleCraftSystemKey2026!?!?";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    private static final long EXP_TIME_MS = 1000 * 60 * 60 * 2;

    public static String generateToken (String email, String role){
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXP_TIME_MS);

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String validateAndExtractEmail (String token){
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build();

            Claims claims = parser.parseSignedClaims(token).getPayload();

            return claims.getSubject();
        }
        catch (Exception e){
            return null;
        }

    }

}

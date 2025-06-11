package com.api.notifications.push.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private Key signingKey;
    private final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String create(String id, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey, SignatureAlgorithm.HS256);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }
}

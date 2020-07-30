package com.antoniosanzc.spring.boot.token.example.authentication.core;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SessionManager {

	private static final Logger log = LoggerFactory.getLogger(SessionManager.class);

    private final Key key;
    private ApiGatewaySettings apiGatewaySettings;

    @Autowired
    public SessionManager (ApiGatewaySettings apiGatewaySettings)
    {
        this.apiGatewaySettings = apiGatewaySettings;
        this.key = new SecretKeySpec(apiGatewaySettings.getSecret().getBytes(), apiGatewaySettings.getKeyAlgorithm());
    }

    public String generateSession (String username) {

        long ttl = apiGatewaySettings.getTokenTimeToLiveInSeconds() * 1000;
        Date expiration = new Date(Instant.now().toEpochMilli() + ttl);

//      Jwts can have different usages: authentication mechanisms, url-safe encoding, 
//      securely encoding, data expiration, etc. Regardless of how you will use your Jwt, 
//      the mechanism to construct and verify it are the same.
    
//      Set up JWT claims
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public boolean isSessionValid (String session)
    {
        try {
            String subject = Jwts.parser().setSigningKey(key).parseClaimsJws(session).getBody().getSubject();
            log.info("Session key for user '{}' is valid", subject);
            return !StringUtils.isEmpty(subject);
        } catch (Exception e) {
            log.info("Session key seems invalid ({})", e.getMessage());
            return false;
        }
    }
}

package com.alam.SpringSecurity.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretKey;
    public JwtService() {
        secretKey = generateSecretKey();
    }

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims)
                            .subject(username)
                            .issuedAt(new Date(System.currentTimeMillis()))
                            .expiration(new Date(System.currentTimeMillis() + 1000*60*20))
                            .and()
                            .signWith(getKey(), SignatureAlgorithm.HS256)
                            .compact();
    }

    private Key getKey() {
        byte[] KeyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public String generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            return secretKey;
        } catch (Exception e) {
            throw new RuntimeException("Secret Key not build");
        }
    }

    /*
     * Below methods are for verifying token.
    */

    //Extracting username from the claim
    public String extractuserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllCalims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllCalims(String token) {
        return Jwts.parser().setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    //validating token from the username
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractuserName(token);
        return (username.equals(userDetails.getUsername()) && !(isTokenExpired(token)));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}

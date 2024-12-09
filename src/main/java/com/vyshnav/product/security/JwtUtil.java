package com.vyshnav.product.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


//key generate class


@Component
public class JwtUtil {

    private static String secretKey;
    JwtUtil(){
        SecureRandom random = new SecureRandom();
        byte[] key =new byte[32]; // 256 bits
        random.nextBytes(key);
        secretKey = Base64.getEncoder().encodeToString(key);
    }

    public String generateToken(String username, List<String> roles){
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*6*2))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // validation
    public Boolean vaildToken(String token, String username){
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }

    // this is used to extract the username
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // this is used to extract the expiration date
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    // if expired this token will be used
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // to extract the roles
    public List<String> extractRole(String token){
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }


    //extraction of token details this method is written
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

}

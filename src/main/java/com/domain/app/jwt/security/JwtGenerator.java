package com.domain.app.jwt.security;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.domain.app.jwt.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	@Value("${jwt.key}")
	private String jwtSecretKey;
	
	@Value("${jwt.expire.minutes}")
	private Integer expirationTime;

    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());
        
        Calendar currentTime = Calendar.getInstance();
		currentTime.add(Calendar.MINUTE, this.expirationTime);
		Date expiration = currentTime.getTime();

        return Jwts.builder()
                .setClaims(claims)
               // .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
}

package com.domain.app.jwt.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.domain.app.jwt.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	@Value("${jwt.key}")
	private String jwtSecretKey;
	
    public JwtUser validate(String token) {
        JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();

			jwtUser = new JwtUser();

			if(body.getSubject() == null || body.get("userId") == null || body.get("role") == null) {
				return null;
			}
			//Can add DB check for UserID and Role
			jwtUser.setUserName(body.getSubject());
			jwtUser.setId(Long.parseLong((String) body.get("userId")));
			jwtUser.setRole((String) body.get("role"));
		} catch (Exception e) {
			System.out.println("JWT is invalid" + e.getMessage());
			throw new RuntimeException(e);
		}

        return jwtUser;
    }
}

package com.security.demosecurity3.sevrice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import com.security.demosecurity3.models.Users;

import io.jsonwebtoken.*;

public class JwtService2 {
	private final Logger logger = LoggerFactory.getLogger(JwtService2.class);
	private String jwtSecret = "anymail@gmail.com";
	private int jwtEXpriration = 86400;

	public String generateToken(Authentication authentication) {
		Users user = (Users) authentication.getPrincipal();
		return Jwts.builder().setSubject(user.getUser_name())
				.setIssuedAt(new Date(new Date().getTime() + jwtEXpriration))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT sinature ->Message:{}", e);
		} catch (MalformedJwtException e) {
			logger.error("The token invalid format ->Message:{}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT Token ->Message:{}", e);
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT Token ->Message:{}", e);
		} catch (IllegalArgumentException e) {
			logger.error("Jwt claims string is empty ->Message:{}", e);
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return username;
	}
}

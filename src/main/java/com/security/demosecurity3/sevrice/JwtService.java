package com.security.demosecurity3.sevrice;

import java.util.Collection;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.security.demosecurity3.models.Users;
import com.security.demosecurity3.repository.RoleRepository;
import com.security.demosecurity3.repository.UsersRepository;

@Service
public class JwtService {
	@Value("${secret.key}")
	private String secretkey;

	public String generateToken(Users user, Collection<SimpleGrantedAuthority> authorities) {
		Algorithm algorithm = Algorithm.HMAC256(secretkey.getBytes());
		return JWT.create().withSubject(user.getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis() + (50 * 60 * 1000)))
				.withClaim("roles",
						authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
	}

	public String generateRefreshToken(Users user, Collection<SimpleGrantedAuthority> authorities) {
		Algorithm algorithm = Algorithm.HMAC256(secretkey.getBytes());
		return JWT.create().withSubject(user.getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis() + (70 * 60 * 1000))).sign(algorithm);
	}

}

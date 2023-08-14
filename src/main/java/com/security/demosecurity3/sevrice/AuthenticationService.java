package com.security.demosecurity3.sevrice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.security.demosecurity3.auth.AuthenticationRequest;
import com.security.demosecurity3.auth.AuthenticationRespone;
import com.security.demosecurity3.auth.RegisterRequest;
import com.security.demosecurity3.models.Role;
import com.security.demosecurity3.models.Users;
import com.security.demosecurity3.repository.RoleCustomRepo;
import com.security.demosecurity3.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UsersRepository usersRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final RoleCustomRepo roleCustomRepo;
	private final UserService userService;

	public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
		try {
			Users user = usersRepository.findByEmail(authenticationRequest.getEmail())
					.orElseThrow(() -> new NoSuchElementException("User Not Found!!"));
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
			List<Role> role = null;
			if (user != null) {
				role = roleCustomRepo.getRole(user);
			}

			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();	
			Set<Role> set = new HashSet<>();
			role.stream().forEach(c -> {	
			//	set.add(new Role(c.getName()));									
				authorities.add(new SimpleGrantedAuthority(c.getName()));
			});

			user.setRoles(set);
			set.stream().forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));
			var jwtAccessToken = jwtService.generateToken(user, authorities);
			var jwtRefreshToken = jwtService.generateRefreshToken(user, authorities);
			return ResponseEntity.ok(AuthenticationRespone.builder().access_token(jwtAccessToken)
					.refresh_token(jwtRefreshToken).email(user.getEmail()).user_name(user.getUser_name()).role(authorities).build());

		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body("Invalid Credential");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Somthing went wrong!!");
		}
	}

	public ResponseEntity<?> register(RegisterRequest registerRequest) {
		try {
			if (usersRepository.existsById(registerRequest.getEmail().toString())) {
				throw new IllegalArgumentException(
						"User with " + registerRequest.getEmail() + " email already exist!!!");
			}

			userService.saveUsers(new Users(registerRequest.getPhone(), registerRequest.getUser_name(),
					registerRequest.getEmail(), registerRequest.getPassword(), new HashSet<>()));
			System.out.println(registerRequest.getPassword() + "pass encode");
			userService.addtoUser(registerRequest.getEmail(), "ROLE_USER");

			Users user = usersRepository.findByEmail(registerRequest.getEmail()).orElseThrow();
			return ResponseEntity.ok(user);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}

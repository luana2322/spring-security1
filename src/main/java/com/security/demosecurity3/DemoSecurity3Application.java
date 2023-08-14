package com.security.demosecurity3;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.demosecurity3.models.Role;
import com.security.demosecurity3.models.Users;
import com.security.demosecurity3.sevrice.UserService;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories

public class DemoSecurity3Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurity3Application.class, args);

	}

	@Bean	 
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CommandLineRunner run(UserService userService) {
//		
//		return args->{
//			
//			userService.saveRole(new Role(null,"ROLE_USER","This is user"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN","This is admin"));
//			userService.saveRole(new Role(null,"ROLE_MANAGER","This is manager"));
//			
//			userService.saveUsers(new Users("9999999999", "user1","user1@gmail.com", "pass1", new HashSet<>()));
//			userService.saveUsers(new Users("9999999998", "user2","user2@gmail.com", "pass2", new HashSet<>()));
//			userService.saveUsers(new Users("999999997", "user3","user3@gmail.com", "pass3", new HashSet<>()));
//			
//			userService.addtoUser("user1@gmail.com", "ROLE_USER");
//			userService.addtoUser("user2@gmail.com", "ROLE_ADMIN");
//			userService.addtoUser("user3@gmail.com", "ROLE_MANAGER");
//			
//		};
//	}

}

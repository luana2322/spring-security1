package com.security.demosecurity3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;

//@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		http.csrf().disable();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeHttpRequests()	//wrong this line
//			.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//			.requestMatchers("/api/v1/auth/**").permitAll()
//			.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
//			.requestMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
//			.and()
//			.csrf().disable()
//			.cors(Customizer.withDefaults())
//			.authorizeRequests()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//			.authenticationProvider(authenticationProvider);
//		
//			return http.build();
//			
//			}
//	WRONG
	
	@Bean
    public SecurityFilterChain securityFilterChain1	(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .requestMatchers("/api/v1/auth/**").permitAll()
            .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
            .and()
            .csrf().disable()
            .cors(Customizer.withDefaults())
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(authenticationProvider);

        return http.build();
    }

	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.authorizeRequests()
	            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	            .requestMatchers("/api/v1/auth/**").permitAll()
	            .requestMatchers("/demo/admin/**").hasAnyAuthority("ROLE_ADMIN")
	            .requestMatchers("/demo/user/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
	            .and()
	            .csrf().disable()
	            .cors(Customizer.withDefaults())
	            .authorizeRequests()
	            .anyRequest()
	            .authenticated()
	            .and()
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .authenticationProvider(authenticationProvider);

	        return http.build();
	    }
	  

	  
}

package com.security.demosecurity3.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.security.demosecurity3.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRespone {
private String access_token;
private String refresh_token;
private String user_name;
private String email;
private Collection<? extends GrantedAuthority> role;
}

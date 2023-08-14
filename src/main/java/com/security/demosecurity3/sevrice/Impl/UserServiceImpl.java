package com.security.demosecurity3.sevrice.Impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.demosecurity3.models.Role;
import com.security.demosecurity3.models.Users;
import com.security.demosecurity3.repository.RoleRepository;
import com.security.demosecurity3.repository.UsersRepository;
import com.security.demosecurity3.sevrice.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public Users saveUsers(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>());

		return usersRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {

		return roleRepository.save(role);
	}

	@Override
	public void addtoUser(String user_name, String Rolename) {
		if(!usersRepository.findByEmail(user_name).isPresent()) {
			throw new IllegalArgumentException("User with email "+user_name+" does not exist!!!");
		}
		Role role=roleRepository.findByName(Rolename);
		
		if(role==null) {
			throw new IllegalArgumentException("Role with name "+Rolename+" does not exist!!!");
		}
		Users user=usersRepository.findByEmail(user_name).get();
		
		user.getRoles().add(role);
	}

}

package com.security.demosecurity3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.demosecurity3.models.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, String>{

	Optional<Users> findByEmail(String email);
	
}

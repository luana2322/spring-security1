package com.security.demosecurity3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.demosecurity3.models.Role;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String role); 
}

package com.doranco.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.security.entities.User;

public interface IUserRepository extends JpaRepository <User, Long> {
	
	Optional<User> findByEmail (String email);
}

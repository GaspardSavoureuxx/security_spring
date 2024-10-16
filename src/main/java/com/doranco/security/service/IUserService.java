package com.doranco.security.service;

import java.util.Optional;

import com.doranco.security.entities.User;

public interface IUserService {
	
	 User addProf(User user);
	 User addStudent (User user);
	 String login (String email, String password);
	 Optional<User> getUserByEmail(String email);
}

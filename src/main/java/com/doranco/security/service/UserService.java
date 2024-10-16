package com.doranco.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doranco.security.entities.User;
import com.doranco.security.enums.EnumRole;
import com.doranco.security.repository.IUserRepository;

@Service
public class UserService implements IUserService {
	
	
	@Autowired
	IUserRepository iUserRepository;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public User addProf(User user) {
		user.getRoles().add(EnumRole.PROF);
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return iUserRepository.save(user);
	}


	@Override
	public User addStudent(User user) {
		user.getRoles().add(EnumRole.STUDENT);
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return iUserRepository.save(user);
	}


	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		 return null;
	}
	
	  @Override
	    public Optional<User> getUserByEmail(String email) {
	        return iUserRepository.findByEmail(email);
	    }
	}



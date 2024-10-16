package com.doranco.security.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doranco.security.entities.User;
import com.doranco.security.service.UserService;
import com.doranco.security.utils.UserRoleExtractor;


@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity getMyData ( Authentication authentication ) {
		
		String email = authentication.getName();
		
		Optional<User> userFind = userService.getUserByEmail(email);
		// Le name correspond à l'email ici, par ce que c'est ce qu'on avait définit dans la méthode getUsername de User
		
		if(userFind.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userFind.get());
	}
	
	
	@GetMapping("hello")
	public ResponseEntity sayHello( Authentication authentication ) {
		if ( UserRoleExtractor.isUserProf(authentication)) {
			return ResponseEntity.ok("Bonjour professeur");
		}
		if ( UserRoleExtractor.isUserStudent(authentication)) {
			return ResponseEntity.ok("Bonjour étudiant");
		}
		return ResponseEntity.ok("T'es qui mec ?");
	}

}

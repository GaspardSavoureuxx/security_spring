package com.doranco.security.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doranco.security.entities.User;
import com.doranco.security.security.JwtService;
import com.doranco.security.service.IUserService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	IUserService iUserService;
	
	 @Autowired
	PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 JwtService jwtService;
	 
	@PostMapping("add_prof")
	public ResponseEntity addProf(@RequestBody User user) {
		return ResponseEntity.ok(iUserService.addProf(user));
	}
	
	@PostMapping("add_student")
	public ResponseEntity addStudent(@RequestBody User user) {
		return ResponseEntity.ok(iUserService.addStudent(user));
		}
	
	
	
	@PostMapping("login")
	public ResponseEntity login (@RequestBody Map<String, String> request ) {
		
		String email = request.get("email");
        String password = request.get("password");
        
		Optional<User> user = iUserService.getUserByEmail(email);
		
		if (user.isEmpty())
            return new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.NOT_FOUND);

        if (!passwordEncoder.matches(password, user.get().getPassword()))
            return new ResponseEntity("Mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        
        String jwt = jwtService.generateToken(user.get());
        
        return ResponseEntity.ok(jwt);
    }
	// creation d'une fonction login avec une map
	// sans passer par la creation d'une classe Dto
}

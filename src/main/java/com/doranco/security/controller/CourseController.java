package com.doranco.security.controller;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doranco.security.entities.Course;
import com.doranco.security.enums.EnumRole;
import com.doranco.security.service.ICourseService;
import com.doranco.security.utils.UserRoleExtractor;


@RestController
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	ICourseService courseService;
	
	@GetMapping("{id}")
	public ResponseEntity getCourse( @PathVariable Long id) {
		Optional<Course> course = courseService.getCourseById(id);
		
		if (course.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(course.get());
    }
	 
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('PROF')")
	public ResponseEntity createCourse (@RequestBody Course course, Authentication authentication ) {
		
		
		// if (!UserRoleExtractor.isUserProf(authentication)) 
		//	return new ResponseEntity("Vous avez pas le droit", HttpStatus.FORBIDDEN);
				
		return ResponseEntity.ok(courseService.createCourse(course));
		
		// Si l'user est un prof il peut créer un cours, sinon FROBIDDEN
	}
	
	

/*	
	public boolean isUserProf(Authentication authentication) {
		boolean isProf = false;
		Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(EnumRole.PROF.toString()))
				isProf = true;
		}
		return isProf;
		
	}
	
	public boolean isUserStudent(Authentication authentication) {
		boolean isStudent = false;
		Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(EnumRole.STUDENT.toString()))
				isStudent = true;
		}
		return isStudent;
		
	}
*/	
}

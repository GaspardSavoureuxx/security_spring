package com.doranco.security.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.doranco.security.enums.EnumRole;

public class UserRoleExtractor {
	
	static public boolean isUserProf(Authentication authentication) {
		boolean isProf = false;
		Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(EnumRole.PROF.toString()))
				isProf = true;
		}
		return isProf;
		
	}
	
	static public boolean isUserStudent(Authentication authentication) {
		boolean isStudent = false;
		Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(EnumRole.STUDENT.toString()))
				isStudent = true;
		}
		return isStudent;
		
	}

}

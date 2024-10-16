package com.doranco.security.service;

import java.util.Optional;

import com.doranco.security.entities.Course;

public interface ICourseService {
	
	Optional<Course> getCourseById (Long id);
	Course createCourse (Course course);

}

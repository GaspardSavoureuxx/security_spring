package com.doranco.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doranco.security.entities.Course;
import com.doranco.security.repository.ICourseRepository;

@Service
public class CourseService implements ICourseService {
	
	@Autowired
	ICourseRepository courseRepository;

	@Override
	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
		
	}

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

}

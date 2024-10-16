package com.doranco.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doranco.security.entities.Course;

@Repository
public interface ICourseRepository extends JpaRepository <Course, Long> {

}

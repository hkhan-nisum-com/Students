package com.Management.Students.repository;

import com.Management.Students.model.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesRepository extends JpaRepository<Grades , Long> {
}

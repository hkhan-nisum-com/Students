package com.Management.Students.Service;

import com.Management.Students.model.Course;
import com.Management.Students.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllcourse(){
        return courseRepository.findAll();
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    public ResponseEntity<Course> updateCourse (Long id , Course course){
        Course course1 = courseRepository.findById(id).orElseThrow();

        course1.setCourseName(course.getCourseName());
        course1.setCourseType(course.getCourseType());

        courseRepository.save(course1);

        return ResponseEntity.ok(course1);
    }

    public ResponseEntity<Course> deleteCourse (Long id){

        Course course = courseRepository.findById(id).orElseThrow();

        courseRepository.delete(course);

        return ResponseEntity.ok(course);
    }


}

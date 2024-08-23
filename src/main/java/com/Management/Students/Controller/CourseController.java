package com.Management.Students.Controller;


import com.Management.Students.Service.CourseService;
import com.Management.Students.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public List<Course> getAllCourse (){
        return courseService.getAllcourse();
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse (@PathVariable Long id ,@RequestBody Course course){
        Course course1 = courseService.updateCourse(id,course).getBody();

        return ResponseEntity.ok(course1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Course> deleteCourse (@PathVariable Long id){

        ResponseEntity<Course> course = courseService.deleteCourse(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

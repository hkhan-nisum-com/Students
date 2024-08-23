package com.Management.Students.Controller;

import com.Management.Students.Dto.StudentDto;
import com.Management.Students.Service.StudentService;
import com.Management.Students.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;




    @GetMapping
    public List<StudentDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/student")
    public List<StudentDto> getStudents(
                                     @RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName) {

        return studentService.getStudents(firstName,lastName);
    }


    @PostMapping
    public StudentDto CreateStudent (@RequestBody @Valid StudentDto studentDto ) {

        return studentService.CreateStudent(studentDto);
    }

    @PutMapping()
    public ResponseEntity<StudentDto> UpdateStudent ( @RequestBody StudentDto studentDto){
        StudentDto student = studentService.UpdateStudent(studentDto).getBody();

        return  ResponseEntity.ok(student);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<StudentDto> DeleteStudent (@PathVariable Long id){
        ResponseEntity<StudentDto> student = studentService.DeleteStudent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<Student> assignCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentService.assignCourse(studentId, courseId);
        return ResponseEntity.ok(student);
    }



}

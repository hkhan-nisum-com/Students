package com.Management.Students.Service;


import com.Management.Students.Dto.StudentDto;
import com.Management.Students.model.Course;
import com.Management.Students.model.Student;
import com.Management.Students.repository.CourseRepository;
import com.Management.Students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.Management.Students.ModelMapper.ModelConverter.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public StudentService(
            StudentRepository studentRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public List<StudentDto> getAllStudent(){
        List<Student> students = studentRepository.findAll();
        return convertToStudentListDto(students);
    }


    public List<StudentDto> getStudents(String firstName, String lastName){
        List<Student> student = studentRepository.getStudents(firstName,lastName);
        return convertToStudentListDto(student);
    }


    public StudentDto CreateStudent (StudentDto studentDto) {
            Student student = convertToEntity(studentDto);
//            if (studentRepository.existsByLastName(student.getLastName())){
//                throw new ResoureNotFound("A student with already exist with last name " + student.getLastName());
//            }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
            Student studentCreated =  studentRepository.save(student);
            return convertToDto(studentCreated);

    }

    public Student authenticate(StudentDto studentDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        studentDto.getEmail(),
                        studentDto.getPassword()
                )
        );

        return studentRepository.findByEmail(studentDto.getEmail())
                .orElseThrow();
    }


    public ResponseEntity<StudentDto> UpdateStudent (StudentDto studentDto){
        Student student = studentRepository.findById(studentDto.getId()).orElseThrow();

        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmailId(studentDto.getEmail());


        studentRepository.save(student);

        StudentDto updatedStudentDto = new StudentDto();

        updatedStudentDto.setId(student.getId());
        updatedStudentDto.setFirstName(student.getFirstName());
        updatedStudentDto.setLastName(student.getLastName());
        updatedStudentDto.setEmailId(student.getEmail());


        return ResponseEntity.ok(updatedStudentDto);
    }

    public ResponseEntity<StudentDto> DeleteStudent (Long id){
        Student student = studentRepository.findById(id).orElseThrow();

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmailId(student.getEmail());

        studentRepository.delete(student);

        return ResponseEntity.ok(studentDto);
    }

    public Student assignCourse (Long StudentId , Long CourseId){

        Student student = studentRepository.findById(StudentId).orElseThrow();
        Course course = courseRepository.findById(CourseId).orElseThrow();

        student.getCourses().add(course);

        return studentRepository.save(student);

    }






}

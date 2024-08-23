package com.Management.Students.Testing;


import com.Management.Students.Dto.StudentDto;
import com.Management.Students.Service.StudentService;
import com.Management.Students.exception.ResoureNotFound;
import com.Management.Students.model.Student;
import com.Management.Students.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {



    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStudent_WhenLastNameExists_ShouldThrowResourceNotFound() {
        // Arrange
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("humza");
        studentDto.setLastName("khan");
        studentDto.setEmailId("humza@gmail.com");

        Student student = new Student();
        student.setFirstName("humza");
        student.setLastName("khan");
        student.setEmailId("humza@gmail.com");

        when(studentRepository.existsByLastName(studentDto.getLastName())).thenReturn(true);


        // Act & Assert
        ResoureNotFound thrown = assertThrows(
                ResoureNotFound.class,
                () -> studentService.CreateStudent(studentDto),
                "Expected CreateStudent() to throw ResourceNotFound, but it didn't"
        );

        assertEquals("A student with already exist with last name khan", thrown.getMessage());
    }

    @Test
    public void testCreateStudent_WhenLastNameDoesNotExist_ShouldCreateStudent() {
        // Arrange
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("humza");
        studentDto.setLastName("khan");
        studentDto.setEmailId("humza@gmail.com");

        Student student = new Student();
        student.setFirstName("humza");
        student.setLastName("khan");
        student.setEmailId("humza@gmail.com");

        Student savedStudent = new Student();
        savedStudent.setFirstName("humza");
        savedStudent.setLastName("khan");
        savedStudent.setEmailId("humza@gmail.com");

        when(studentRepository.existsByLastName(anyString())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        // Act
        StudentDto result = studentService.CreateStudent(studentDto);

        // Assert
        assertNotNull(result);
        assertEquals("khan", result.getLastName());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void updateStudent_ShouldReturnUpdatedStudentDto() {
        // Arrange
        // Initialize existingStudent
        Student existingStudent = new Student();
        existingStudent.setFirstName("Humza");
        existingStudent.setLastName("Khan");
        existingStudent.setEmailId("humza@gmail.com");

        // Initialize studentDto
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("maaz");
        studentDto.setLastName("khan");
        studentDto.setEmailId("maaz@gmail.com");

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(existingStudent);

        // Act
        ResponseEntity<StudentDto> response = studentService.UpdateStudent(studentDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        StudentDto updatedDto = response.getBody();
        assertNotNull(updatedDto);
        assertEquals("maaz", updatedDto.getFirstName());
        assertEquals("khan", updatedDto.getLastName());
        assertEquals("maaz@gmail.com", updatedDto.getEmail());
    }

    @Test
    void deleteStudent_ShouldReturnDeletedStudentDto() {
        // Arrange
        // Initialize existingStudent
        Student existingStudent = new Student();
        existingStudent.setFirstName("maaz");
        existingStudent.setLastName("khan");
        existingStudent.setEmailId("maaz@gmail.com");

        // Initialize studentDto
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("maaz");
        studentDto.setLastName("khan");
        studentDto.setEmailId("maaz@gmail.com");
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(existingStudent));
        doNothing().when(studentRepository).delete(any(Student.class));

        // Act
        ResponseEntity<StudentDto> response = studentService.DeleteStudent(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        StudentDto deletedDto = response.getBody();
        assertNotNull(deletedDto);
        assertEquals("maaz", deletedDto.getFirstName());
        assertEquals("khan", deletedDto.getLastName());
        assertEquals("maaz@gmail.com", deletedDto.getEmail());
    }



}

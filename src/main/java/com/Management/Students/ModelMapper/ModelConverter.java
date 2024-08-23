package com.Management.Students.ModelMapper;

import com.Management.Students.Dto.StudentDto;
import com.Management.Students.model.Student;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

public class ModelConverter {

    private ModelConverter() {
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static StudentDto convertToDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    public static  Student convertToEntity (StudentDto studentDto){
        Student student = modelMapper.map(studentDto, Student.class);
        return student;
    }

    public static List<StudentDto> convertToStudentListDto(List<Student> students) {
        return Arrays.asList(modelMapper.map(students, StudentDto[].class));
    }

}
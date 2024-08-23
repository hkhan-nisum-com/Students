package com.Management.Students.Service;

import com.Management.Students.model.Grades;
import com.Management.Students.model.Student;
import com.Management.Students.repository.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradesService {

    @Autowired
    GradesRepository gradesRepository;

    public List<Grades> getAllStudentGrades(){
        return gradesRepository.findAll();
    }

    public Grades addGrades (Grades grades){

        return gradesRepository.save(grades);
    }


}

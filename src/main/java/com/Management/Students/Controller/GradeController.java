package com.Management.Students.Controller;

import com.Management.Students.Service.GradesService;
import com.Management.Students.model.Grades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    GradesService gradesService;

    @GetMapping
    public List<Grades> getAllStudentGrades(){
        return gradesService.getAllStudentGrades();
    }

    @PostMapping
    public Grades addGrades (@RequestBody Grades grades){
        return gradesService.addGrades(grades);
    }

}

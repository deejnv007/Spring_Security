package com.spring.security.controller;

import com.spring.security.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
         new Student(1,"Shiv",95),
         new Student(2, "Abhi", 96),
         new Student(3,"Dee", 69)
    ));

    @GetMapping()
    public List<Student> getStudents(){
      return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
       return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}

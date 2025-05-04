package com.learning.restdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.restdemo.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    public List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("William", "Chane"));
        theStudents.add(new Student("William", "Schwarz"));
        theStudents.add(new Student("William", "Warlid"));
    }
    
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if((studentId >= theStudents.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId); 
    }

}

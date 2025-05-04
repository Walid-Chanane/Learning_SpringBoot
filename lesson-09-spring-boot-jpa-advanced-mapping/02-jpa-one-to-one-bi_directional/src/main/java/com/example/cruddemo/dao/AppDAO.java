package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetails;

public interface AppDAO {
    
    void save(Instructor theInstructor);

    Instructor findInstructurById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetails findInstructorDetailsById(int theId);

    void deleteInstructorDetailsById(int theId);
}

package com.example.cruddemo.dao;

import java.util.List;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetails;

public interface AppDAO {
    
    void save(Instructor theInstructor);

    Instructor findInstructurById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetails findInstructorDetailsById(int theId);

    void deleteInstructorDetailsById(int theId);

    List<Course> findCoursesByIntructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor theInstructor);

    void update(Course theCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsById(int theId);
}

package com.learning.cruddemo.dao;

import com.learning.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findall();

    List<Student> findByLastName(String lastname);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteall();

}

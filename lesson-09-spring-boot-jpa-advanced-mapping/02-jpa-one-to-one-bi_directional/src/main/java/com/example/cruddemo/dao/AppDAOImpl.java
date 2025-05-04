package com.example.cruddemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetails;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{
    
    //definen field for entity manager
    private EntityManager entityManager;
    
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) { //this is for saving an object(theInstructor) in the data base
    //creating an object is another matter, an will be handeled in the main app or smwhere else
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructurById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //this will also delete the instructor details
        
        //retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetails findInstructorDetailsById(int theId) {

        return entityManager.find(InstructorDetails.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {

        //find the instroctor details
        InstructorDetails tempInstructorDetails = entityManager.find(InstructorDetails.class, theId);

        //delete the instructor details which will also delete the instructor (cascading)
        entityManager.remove(tempInstructorDetails);
    }
}

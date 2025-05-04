package com.learning.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager ;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager ;
    }

    @Override
    public List<Employee> findAll() {

        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        //execute query and get result 
        List<Employee> employees = theQuery.getResultList(); 

        //return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //return employee 
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        //save employee 
        Employee dbEmployee = entityManager.merge(theEmployee);

        //return dbemployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //remove employee
        entityManager.remove(theEmployee);
    }

}
package com.learning.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learning.cruddemo.entity.Employee;

//to change the endpoint of Employee repository(depot)
// instead of acces it by /employees. we use /members 
//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    //that's it ... no need to write any code
}

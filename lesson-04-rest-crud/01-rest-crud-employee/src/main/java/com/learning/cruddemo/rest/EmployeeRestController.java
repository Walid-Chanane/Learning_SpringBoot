package com.learning.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.cruddemo.entity.Employee;
import com.learning.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
     
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // expose /employees and get a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);
        if ( theEmployee == null ){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        
        return theEmployee;
    }

    //add mapping for post /employees - new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //in case they pass an id in JSON... set id to 0 
        //this will force to save a new item .. instead of update

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //update an employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for deleting employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = employeeService.findById(employeeId);

        //throw exception if null
        if(tempEmployee == null)
        throw new RuntimeException("Employee id not found - " + employeeId);

        employeeService.deleteById(employeeId);
        
        return "employee with id = " + employeeId + " deleted.";
    }
}

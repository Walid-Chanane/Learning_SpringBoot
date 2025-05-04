package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //this is where we add all of our related advices for logging
    
    //@Before("execution(public void addAccount())")       //for all addAccount() methods
    //@Before("execution(public void com.example.aopdemo.dao.AccountDAO.addAccount())") //only for addAccount in AccountDAO
    //@Before("execution(public void add*())") //for every method starting with "add" (and returning void)
    //@Before("execution(* add*())") //for every type and every mrthod starting with "add"
    //@Before("execution(* add*(com.example.aopdemo.Account))")// .. taking an Account type object as an argument (the long format is necessary)
    //@Before("execution(* add*(com.example.aopdemo.Account,..))")//.. argument : account + any number of any type arguments
    //@Before("execution(* add*(..))")// .. any number/types of arguments
    @Before("execution(* com.example.aopdemo.dao.*.*(..))")//any method in the dao package + any return type ...
    public void beforeAddAccountAdvice(){
        System.out.println("\n  Executing @Before advice on addAccount() method");
    }
}

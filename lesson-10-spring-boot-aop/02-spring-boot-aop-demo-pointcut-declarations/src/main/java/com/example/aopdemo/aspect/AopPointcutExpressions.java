package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect //optional if we obly have pointcut in the class, only necessary if we have advices
public class AopPointcutExpressions {
    
    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDAOPackage(){}

    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    public void getters(){}

    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    public void setters(){}
    
    @Pointcut("forDAOPackage() && !(getters() || setters())")
    public void allDAOExceptGettersAndSetters(){}

}

package com.luv2code.springboot.thymeleafdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
    
    //set logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDAOPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage() ")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){ //joinpoint give us meta data on the method call

        //display method we are calling
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("=========> in @Before : calling method : " + method);

        //display the arguments to the method
        
        //get the arguments
        Object[] args = theJoinPoint.getArgs();

        //loop through and display the arguments
        for(Object tempArg : args){
            myLogger.info("==> Argument : " + tempArg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void aferReturning(JoinPoint theJoinPoint, Object result){

        //display method we are returning from
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("=========> in @AfterReturning : from method : " + method);

        //diplay data returned
        myLogger.info("==> The result : " + result);

    }
}

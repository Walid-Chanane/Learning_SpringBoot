package com.example.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    
    @Before("com.example.aopdemo.aspect.AopPointcutExpressions.allDAOExceptGettersAndSetters()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n  Executing @Before advice on addAccount() method");
    
    //display the method signature
    MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
    System.out.println("  Method : " + methodSignature);

    //display the method arguments
    //get arguments
    Object[] args = theJoinPoint.getArgs();

    //loop through args
    for(Object tempArg : args){
        System.out.println(tempArg);
        if(tempArg instanceof Account){
            //downcast and print Account specific stuff
            Account theAccount = (Account) tempArg;
            
            System.out.println("Account name : " + theAccount.getName() 
                                + " | Account level : " + theAccount.getLevel());
        }
    }
    }


    //add a new advice for AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
                    returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){

        //print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n========> Executing @AfterReturning on method : " + method);

        //print out the result of the method call
        System.out.println("========>the result is : " + result);

        //let's post-process the data... let's modify it hehehe
        //convert the accounts name to uppercase
        convertAccountsNamesToUppercase(result);

        System.out.println("========>the result is : " + result);

    }


    private void convertAccountsNamesToUppercase(List<Account> result) {
        //loop through the account
        for(Account tempAccount : result){

            //get uppercase version of the names
            String uppercase = tempAccount.getName().toUpperCase();

            //update the names on the accounts
            tempAccount.setName(uppercase);

        }
    }


    @AfterThrowing(pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
                    throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc){

        //print out which method we are advicing on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n========> Executing @AfterThrowing on method : " + method);

        //log the exception
        System.out.println("=========> The exception is : " + exc);
    }


    @After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        //print out which method we are advicing on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n========> Executing @After (Finally) on method : " + method);
    }


    /*@Around("execution(* com.example.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object AroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        
        //print out the method we are advicing on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n========> Executing @Around on method : " + method);
        
        //get begin timestamp
        long begin = System.currentTimeMillis();

        //now let's execute the method
        Object result = null;
        

        //the exception will never get to the main method, it will be handled and customized here.
        //the main method will only get a normal string message
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            //log the exception
            System.out.println(e.getMessage());

            //give user a custom message
            result = "Major accident! but no worries, your private AOP helicopter is on the way!";
        }
        

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end - begin;
        System.out.println("=====> the duration : " + duration / 1000.0 + " seconds");
        
        return result;
    }*/

    @Around("execution(* com.example.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object AroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        
        //print out the method we are advicing on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n========> Executing @Around on method : " + method);
        
        //get begin timestamp
        long begin = System.currentTimeMillis();

        //now let's execute the method
        Object result = null;
        
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            //log the exception
            System.out.println(e.getMessage());

            //rethrow exception
            throw e;
        }
        

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end - begin;
        System.out.println("=====> the duration : " + duration / 1000.0 + " seconds");
        
        return result;
    }
}

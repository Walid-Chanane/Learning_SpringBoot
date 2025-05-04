package com.learning.coaching.common;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class CricketCoach implements Coach {

    public CricketCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @PostConstruct
    public void bobstartup(){
        System.out.println("In bobstartup methode : " + getClass().getSimpleName());
    }

    @PreDestroy
    public void bobthedestroyer(){
        System.out.println("In bobthedestroyer methode : " + getClass().getSimpleName());
    }

    @Override
    public String getdailyworkout(){
        return "Practice fast bowling for 15 minutes.";
    }
}
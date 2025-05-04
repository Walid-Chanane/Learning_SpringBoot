package com.learning.coaching.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    public CricketCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @Override
    public String getdailyworkout(){
        return "Practice fast bowling for 15 minutes.";
    }
}
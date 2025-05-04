package com.learning.coaching.common;

public class SwimCoach implements Coach {
    
    public SwimCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @Override
    public String getdailyworkout(){
        return "Swim 1000 metres as a warm up" ;
    }
}

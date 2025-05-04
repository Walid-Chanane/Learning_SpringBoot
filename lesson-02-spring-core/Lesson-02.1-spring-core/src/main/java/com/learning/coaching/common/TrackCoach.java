package com.learning.coaching.common;

import org.springframework.stereotype.Component;

@Component
/*@Primary*/
/*@Lazy*/
public class TrackCoach implements Coach {

    public TrackCoach(){
        System.out.println("In constructor " + getClass().getSimpleName());
    }

    @Override
    public String getdailyworkout(){
        return "Run a hard 5km !";
    }
    
}

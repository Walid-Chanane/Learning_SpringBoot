package com.learning.coaching.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.coaching.common.Coach;

@RestController
public class DemoController {
    
    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach")Coach myCoach){
        System.out.println("In constructor " + getClass().getSimpleName());
        this.myCoach = myCoach ;
    }

    @GetMapping("/dailyworkout")
    public String getdailyworkout(){
        return myCoach.getdailyworkout();
    }
}
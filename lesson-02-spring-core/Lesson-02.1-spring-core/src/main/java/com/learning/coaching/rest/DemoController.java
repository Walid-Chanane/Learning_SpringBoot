package com.learning.coaching.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.coaching.common.Coach;

@RestController
public class DemoController {
    
    private Coach myCoach;

    private Coach anotherCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach")Coach myCoach,
                          @Qualifier("cricketCoach")Coach anotherCoach){
        System.out.println("In constructor " + getClass().getSimpleName());
        this.myCoach = myCoach ;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getdailyworkout(){
        return myCoach.getdailyworkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans : coach == anotherCoach , " + (myCoach == anotherCoach);
    }
}
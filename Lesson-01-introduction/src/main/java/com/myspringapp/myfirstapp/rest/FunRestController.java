package com.myspringapp.myfirstapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${coach.name}")
    private String coachname;

    @Value("${team.name}") 
    private String teamname;

    @GetMapping("/teaminfo")
    public String getteaminfo(){
        return "coach : " + coachname + " , team name : " + teamname;
    }

    @GetMapping("/")
    public String sayhello(){
        return "Hello world!";
    }

    @GetMapping("/workout")
    public String dailyworkout(){
        return "100 pushups per day";
    }

    @GetMapping("/fortune")
    public String dailyfortunne(){
        return "Today is your lucky day !";
    }
}
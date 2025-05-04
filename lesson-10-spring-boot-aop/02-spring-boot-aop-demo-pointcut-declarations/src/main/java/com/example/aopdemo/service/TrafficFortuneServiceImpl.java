package com.example.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service //for component scanning apperently
public class TrafficFortuneServiceImpl implements TrafficFortuneService{

    @Override
    public String getFortune() {
        //simulate a delay 
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        }

        //return a fortune 
        return "Expect heavy traffic this morning.";
    }

    @Override
    public String getFortune(boolean tripwire) { //this one is to give us the ability to throw an exception

        if(tripwire){
            throw new RuntimeException("Major accident! Highway is closed!");
        }

        return getFortune();
    }
    
}

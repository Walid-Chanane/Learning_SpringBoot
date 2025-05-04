package com.learning.coaching.config;

import com.learning.coaching.common.Coach;
import com.learning.coaching.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aquatic")  /*we can remove "aquatic" and use swimCoach in the DemoController */
    public Coach swimCoach(){
        return new SwimCoach();
    }
}

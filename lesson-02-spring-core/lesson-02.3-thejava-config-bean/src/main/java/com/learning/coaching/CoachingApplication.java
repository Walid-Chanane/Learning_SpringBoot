package com.learning.coaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication /*(
	scanBasePackages = {"com.learning.coaching","com.learning.util"}
)*/
public class CoachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachingApplication.class, args);
	}

}

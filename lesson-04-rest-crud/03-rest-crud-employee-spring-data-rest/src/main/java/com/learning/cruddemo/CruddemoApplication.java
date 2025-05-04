package com.learning.cruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

}

/* check the pom file */

/*this version is with spring boot data rest so we don't need 
the rest controller neither the serviceclass, spring data rest provide all 
the basic crud methods for us*/


/*we will  need to make some changes in the properties file to customize the 
endpoint path (URL)*/
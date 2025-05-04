package com.example.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetails;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);

			//findInstructor(appDAO);

			//deleteInstructor(appDAO);

			//findInstructorDetails(appDAO);

			deleteInstructorDetails(appDAO);
		};
	}


	private void deleteInstructorDetails(AppDAO appDAO) {
		
		int theId = 2;
		System.out.println("Deleting instructor " + theId + " details");

		appDAO.deleteInstructorDetailsById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetails(AppDAO appDAO) {

		//retrieve instructor detail object 
		int theId = 2;
		InstructorDetails tempInstructorDetails = appDAO.findInstructorDetailsById(theId);

		//print the instructor details
		System.out.println("the instructor details : " + tempInstructorDetails);

		//print the associated instructor
		System.out.println("The associated instructor : " + tempInstructorDetails.getInstructor());

	}

	private void deleteInstructor(AppDAO appDAO) {
		
		int theId=1;
		System.out.println("Deleting the instructor with id : " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("instructor with the id : " + theId + " has been deleted!");

	}

	private void findInstructor(AppDAO appDAO) {
		
		int theId = 1;
		System.out.println("Findong instructor...");

		Instructor tempInstructor = appDAO.findInstructurById(theId);

		System.out.println("The instructor : " + tempInstructor);
		System.out.println("The associated instructor details only: " + tempInstructor.getInstructorDetails());

	}

	//the save method in the AppDaoImpl is to interact with the database and save an exsisting object
	//here we will simply create an object
	public void createInstructor(AppDAO appDAO){
		
		//create the instructor
		Instructor tempInstructor = 
			new Instructor("Chad", "Alive","chadisalive@gmail.com");

		//create the instructor details
		InstructorDetails tempInstructorDetails =
			new InstructorDetails("chadisalive", "lifeisenough");

		//associate the objcts (the 2 table)
		tempInstructor.setInstructorDetails(tempInstructorDetails);

		//save the instructor
		//THEN HERE is when we enteract with the database and save the object
		//the instructorDetials will also be saved automaitically because of CascadeType.ALL
		
		System.out.println("Saving instructor: " + tempInstructor );
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}
}

package com.example.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
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

			//deleteInstructorDetails(appDAO);

			//createInstructorWithCourses(appDAO);

			//findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);

			//updateInstructor(appDAO);

			//updateCourse(appDAO);

			deleteCourse(appDAO);
		};
	}


	private void deleteCourse(AppDAO appDAO) {
		int theId = 12;

		System.out.println("Deleting the course : " + theId);
		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 12;

		//find the course
		System.out.println("Finding course : " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		//update course
		System.out.println("Updating the course...");
		tempCourse.setTitle("the five rings");

		//update in the db
		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor : " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		
		//change/update instructor data ex:last name
		System.out.println("Updating instructor...");
		tempInstructor.setLastName("Temple");

		//update the db data
		appDAO.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		//by using the join fetch in the query we can get the instructor AND courses even if it's lazy
		int theId = 1;

		System.out.println("Finding the instructor id : " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("The instructor : " + tempInstructor);
		System.out.println("The associated courses : " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId = 1;

		//when finding the instructor we dont retrieve the courses since it's a lazy fetch
		// so we gomma retrieve the instructor alone and then the courses by the query,
		// then we inject that result list in the courses of the instructor object
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
	
		System.out.println("instructor info : " + tempInstructor);

		//find courses for instructor
		System.out.println("Finding instructor courses ...");
		List<Course> courses = appDAO.findCoursesByIntructorId(theId);

		//and injecting them (from courses) in the instructor courses
		//associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses : " + tempInstructor.getCourses());

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;

		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("instructor info : " + tempInstructor);
		System.out.println("The associated courses : " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor tempInstructor = 
			new Instructor("susan", "public","susanpublic@gmail.com");

		//create the instructor details
		InstructorDetails tempInstructorDetails =
			new InstructorDetails("susanisalive", "lifeisenough");

		//associate the objcts (the 2 table)
		tempInstructor.setInstructorDetails(tempInstructorDetails);

		//create courses
		Course tempCourse1 = new Course("love is war");
		Course tempCourse2 = new Course("art of war");
		Course tempCourse3 = new Course("the five rings - Miyamoto Musashi");

		//add the courses to the instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);

		System.out.println("Saving the instructor...");
		System.out.println("The courses : " + tempInstructor.getCourses() );

		//save the instructor and the courses(cascading)
		appDAO.save(tempInstructor);

		System.out.println("Done!");
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

		Instructor tempInstructor = appDAO.findInstructorById(theId);

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

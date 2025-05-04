package com.learning.cruddemo;

import com.learning.cruddemo.dao.StudentDAO;
import com.learning.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			
			//createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//myreadStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);
			
			//deleteStudent(studentDAO);

			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		System.out.println("Deleted row count : " + studentDAO.deleteall());
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int id = 6;
		System.out.println("Deleting student with id : " + id);
		studentDAO.delete(id);

	}

	private void updateStudent(StudentDAO studentDAO) {

		//enter the id of the student
		System.out.print("Please enter the id of the student : ");
		Scanner scanner = new Scanner(System.in);
		int theId = scanner.nextInt();

		//retrieve student based on the id
		System.out.println("Retrieving student with id : " + theId + " ...");
		Student myStudent = studentDAO.findById(theId);

		//make the changes to the student
		System.out.println("Updating the student's informations ...");

			//choosing the data to update
			System.out.println("Choose the data you want to update : 1.First name   2.Last name   3.Email ");
			int choice = scanner.nextInt();

			//enter the new data
			System.out.print("Please enter the new data of the student : ");
			String newData = scanner.next();

		switch (choice) {
			case 1 -> myStudent.setFirstName(newData);
			case 2 -> myStudent.setLastName(newData);
			case 3 -> myStudent.setEmail(newData);
		}

		//update the student
		studentDAO.update(myStudent);

		//display the student
		System.out.println(myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//enter the name of the student
		System.out.print("Please enter the last name of the student : ");
		Scanner scanner = new Scanner(System.in);
		String theLastName = scanner.next();

		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName(theLastName);

		//display the list of students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findall();

		//display the list of students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}

	}

	private void myreadStudent(StudentDAO studentDAO) {
		//enter the id of the student
		System.out.print("Please enter the id of the student : ");
		Scanner scanner = new Scanner(System.in);
		int theId = scanner.nextInt();

		//retrieve the student based on the id
		System.out.println("Retrieving student with id : " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("Found the student : " + myStudent);
	}

	private void readStudent(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Crating the student ...");
		Student tempStudent = new Student("Zorlando", "Macaroni", "zorlandothegangsta@pieceofshit.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display the id of the object
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated ID : " + theId);

		//retrieve the student based on the id
		System.out.println("Retrieving student with id : " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("Found the student : " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		//Create the students objects
		System.out.println("Creating 3 new students object ...");
		Student tempStudent0 = new Student("Vanilla", "Montreal", "vanilla.montreal@pieceofshit.com");
		Student tempStudent1 = new Student("Coco", "Barazolla", "coco.barazolla@pieceofshit.com");
		Student tempStudent2 = new Student("Giuseppe", "Pepe", "giuseppe.pepe@pieceofshit.com");

		//Save the students objects
		System.out.println("Saving the 3 students ...");
		studentDAO.save(tempStudent0);
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);

	}

	private void createStudent(StudentDAO studentDAO) {

		//Create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("William", "Schwarz", "william.schwarz@pieceofshit.com");

		//Save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display the student id
		System.out.println("Saved student. Generated ID : " + tempStudent.getId());

	}

}

package com.example.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import com.example.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO membershipDAO
													, TrafficFortuneService trafficFortuneService){
		return runner -> {
			//demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);

			//demoTheAfterReturningAdvice(theAccountDAO);

			//demoTheAfterThrowingAdvice(theAccountDAO);

			//demoTheAfterAdvice(theAccountDAO);

			//demoTheAroundAdvice(trafficFortuneService);

			//demoTheAroundAdviceHandleException(trafficFortuneService);

			demoTheAroundAdviceRethrowException(trafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain program : demoTheAroundAdviceRethrowException");

		System.out.println("Calling getFortune()");

		boolean tripwire = true;
		String data = trafficFortuneService.getFortune(tripwire);

		System.out.println("\nMy fortune is : " + data);

		System.out.println("Finished.");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {

		
		System.out.println("\nMain program : demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripwire = true;
		String data = trafficFortuneService.getFortune(tripwire);

		System.out.println("\nMy fortune is : " + data);

		System.out.println("Finished.");

	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain program : demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("\nMy fortune is : " + data);

		System.out.println("Finished.");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		//call method to find the accounts
		List<Account> myList = null;
		
		try{
			//add a boolean flag to simulate the exception
			boolean tripwire = false;
			myList = theAccountDAO.findAccounts(tripwire);
		} 
		catch (Exception exc) {
			System.out.println("===>Main program : ... caught an exception : " + exc);
		}


		//display the accounts
		System.out.println("\n\nMain progman: demo the after returning advice");
		System.out.println("-----------------------");

		System.out.println(myList);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

		//call method to find the accounts
		List<Account> myList = null;
		
		try{
			//add a boolean flag to simulate the exception
			boolean tripwire = true;
			myList = theAccountDAO.findAccounts(tripwire);
		} 
		catch (Exception exc) {
			System.out.println("===>Main program : ... caught an exception : " + exc);
		}


		//display the accounts
		System.out.println("\n\nMain progman: demo the after returning advice");
		System.out.println("-----------------------");

		System.out.println(myList);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

		//call method to find the accounts
		List<Account> myList = theAccountDAO.findAccounts();

		//display the accounts
		System.out.println("\n\nMain progman: demo the after returning advice");
		System.out.println("-----------------------");

		System.out.println(myList);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		Account theAccount = new Account();
		theAccount.setName("YEGEN");
		theAccount.setLevel("CHALLENGER");
		theAccountDAO.addAccount(theAccount, true);

		theMembershipDAO.addSillyMember();

		theAccountDAO.doWork();

		theMembershipDAO.goToSleep();

		theAccountDAO.setName("oner");

		theAccountDAO.setServiceCode("Silver");

		System.out.println(theAccountDAO.getName()); 

		System.out.println(theAccountDAO.getServiceCode());

	}

}

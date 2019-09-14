package model;
/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 7 
 * Professor: Istiaque Shahriar
 * Date: 2019-03-24
 */

import java.util.regex.Pattern;

/**
 * This class is responsible for holding the personal information of the Account holder
 * */
public class Person {
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String emailAddress;
	
	public Person(String firstName, String lastName, long phoneNumber, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		if(validateEmail(emailAddress))
			this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	/**
	 * Returns the first name concatenated with the last name
	 * @return
	 */
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	private boolean validateEmail(String email) {		
		/*
		 * Using Regex
		 * "."  means match any character
		 * "*"  means match any amount of characters
		 * "\\" means literal character
		 * Pattern.matches compare a Pattern with a string
		 */
			if(Pattern.matches(".*@.*\\.com.*", email)) 
				return true;
			else 
				throw new IllegalArgumentException();
	}
}

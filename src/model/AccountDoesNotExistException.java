package model;

/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 9 
 * Professor: Istiaque Shahriar
 * Date: 2019-04-19
 */

public class AccountDoesNotExistException extends Exception{
	public AccountDoesNotExistException() {
		super("There are no accounts with this number.");
	}
}

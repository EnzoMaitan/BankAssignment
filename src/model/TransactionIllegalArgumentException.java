package model;

/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 9 
 * Professor: Istiaque Shahriar
 * Date: 2019-04-19
 */

public class TransactionIllegalArgumentException extends Exception {
	public TransactionIllegalArgumentException(String errorMessage) {
		super(errorMessage);
	}
	public TransactionIllegalArgumentException() {
		super("The transaction value is not valid.");
	}
	public TransactionIllegalArgumentException(long accountNumber, double amount, String errorOcurrance) {
		super("There was an Illegal transaction with the account "+accountNumber+ " with the following amount "+amount+" " + errorOcurrance);
	}
}

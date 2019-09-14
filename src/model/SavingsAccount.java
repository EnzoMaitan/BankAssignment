package model;

/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 9 
 * Professor: Istiaque Shahriar
 * Date: 2019-04-19
 */

/**
 * This class Inherits from BankAccount.
 * This type of account has interest rate and minimum balance.
 * If the balance is higher or equal to the minimumBalance, the interest will be added monthly
 * */
public class SavingsAccount extends BankAccount {
	private double interestRate;
	private double minimumBalance;
	
	/**
	 * Returs the BankAccount.toString concatenated with the minimum balance and interest rate
	 * */
	public String toString() {
		return "S " + super.toString() +" Minimum Balance: "+minimumBalance+ " Interest Rate: "+ interestRate;
	}
	public SavingsAccount(long accountNumber, Person accHolder, double balance, double interestRate,
			double minimumBalance) {
		super(accountNumber, accHolder, balance);
		this.interestRate = interestRate;
		this.minimumBalance = minimumBalance;
	}
	public SavingsAccount() {
		
	}
	public String getFormattedDisplayForFileWriting() {
		return "S " + super.getFormattedDisplayForFileWriting();
	}
	/**
	 * If the balance is higher or equal to the minimum balance, the interest will be added
	 **/
	public void monthlyAccountUpdate() {
		if(balance >= minimumBalance) {
		 this.balance *= (1+interestRate);
		}
	}
}

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
 * This type of account has a fee, which is deducted monthly from the balance
 * */
public class ChequingAccount extends BankAccount {
	private double fee;
	
	public ChequingAccount(long accountNumber, Person accHolder, double balance, double fee) {
		super(accountNumber, accHolder, balance);
		this.fee = fee;
	}
	public ChequingAccount() {
		
	}
	/**
	 * Returs the BankAccount.toString concatenated with the fee
	 * */
	public String toString() {
		return "C " + super.toString() + " Fee: "+ fee;
	}
	public String getFormattedDisplayForFileWriting() {
		return "C " + super.getFormattedDisplayForFileWriting();
	}
	/**
	 * Deducts the fee from the balance
	 * */
	public void monthlyAccountUpdate() {
		 this.balance -= fee;
	}
}

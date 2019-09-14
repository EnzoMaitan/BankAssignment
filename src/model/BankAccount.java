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
import java.util.Scanner;
/**
 * This class is responsible for giving a template for specific account types.
 * Each BankAccout must have an account holder, account number and balance.
 * */
public abstract class BankAccount {
	protected long accountNumber;
	protected Person accHolder;
	protected double balance;
	
	public BankAccount() {
		
	}
	
	public BankAccount(long accountNumber, Person accHolder, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.accHolder = accHolder;
		this.balance = balance;
	}

	abstract void monthlyAccountUpdate();
	
	/**
	 * Returns all the account information, with the balance formatted to 2 decimal digits
	 * */
	public String toString() {
		java.text.DecimalFormat df;

		df = new java.text.DecimalFormat("#.##");
		return "AccountNumber: "+accountNumber+" Name: "+accHolder.getName()+" Phone number: "+accHolder.getPhoneNumber()
		+" Email address: "+accHolder.getEmailAddress()+" Balance: "+ df.format(balance);
	}
	public String getFormattedDisplayForFileWriting() {
		return accHolder.getName() +" "+ this.accountNumber +" "+accHolder.getPhoneNumber() + accHolder.getEmailAddress() +" "+ this.balance;
	}

	/**
	 * Deposits money to the balance if the amt is positive
	 * */
	public void deposit(double amt) throws TransactionIllegalArgumentException{
		if(amt > 0){ 
			this.balance += amt;
		}
		else
			throw new TransactionIllegalArgumentException(this.accountNumber, amt, "When trying to deposit");
	}
	/**
	 * Withdraws money from the balance if the amt is positive and less than the balance
	 * */
	public void withdraw(double amt) throws TransactionIllegalArgumentException{
		if(this.balance >= amt && amt > 0)  {//if the amount to be removed is higher than the balance, it will return false
			this.balance -= amt; 
		}
		else 
			throw new TransactionIllegalArgumentException(this.accountNumber, amt, "When trying to withdraw");
	}
		
	/**
	 * Checks if the string is a valid email
	 * */
	
	/**
	 * Checks if the accountNumber is already in use
	 * */
	private boolean isAccountNumberAvailable(long[] inUse, long accountNumber) {
		for (int i = 0; i < inUse.length; i++) {
			if(accountNumber == inUse[i])
				return false;
		}
		return true;
	}	
}

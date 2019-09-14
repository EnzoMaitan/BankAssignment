package model;

/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 9 
 * Professor: Istiaque Shahriar
 * Date: 2019-04-19
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class in responsible for managing BankAccounts, it holds each account as well as the maximum and current number of accounts.
 * */
public class Bank {
	private ArrayList<BankAccount> accounts;
	
	/**
	 * Initialize the Bank with an empty list
	 * */
	public Bank() {
		this.accounts = new ArrayList<BankAccount>();
	}
	/**
	 * Adds an account to the "accounts" list
	 * */
	public void addAccount(BankAccount account){			
		this.accounts.add(account);
	}
	/**
	 * Withdraws the amount from the account
	 * @throws TransactionIllegalArgumentException 
	 * @throws AccountDoesNotExistException 
	 *
	 */
	public void withdrawFromAccount(long accountNumber, double amount) throws TransactionIllegalArgumentException, AccountDoesNotExistException{
		int index = findAccount(accountNumber);
		if(index < 0)
			throw new AccountDoesNotExistException();
		accounts.get(index).withdraw(amount);
	}
	/**
	 * Deposits the amount into the account
	 * @throws TransactionIllegalArgumentException 
	 * @throws AccountDoesNotExistException 
	 *
	 */
	public void depositInAccount(long accountNumber, double amount) throws TransactionIllegalArgumentException, AccountDoesNotExistException{
		int index = findAccount(accountNumber);
		if(index < 0)
			throw new AccountDoesNotExistException();
		accounts.get(index).deposit(amount);
	}
	/**
	 * Displays all the accounts and its informations
	 * */
	public ArrayList<String> getAllAccountDetails() throws IOException {
		deleteOutdatedBankoutputFile();
		
		ArrayList<String> accountsInformation = new ArrayList<String>();
		
		for (int i = 0; i < accounts.size(); i++) {
			BankAccount currentAccount = accounts.get(i);
			accountsInformation.add(currentAccount.toString());
			writeAccountInformationOnFile(currentAccount);
		}
		return accountsInformation;
	}
	
	/**
	 * Deletes the bankoutput file, so as to not have outdated data
	 * */
	private void deleteOutdatedBankoutputFile() {
		File file = new File("C:\\Term2\\Lab9\\src\\bankoutput.txt");
		file.delete();
	}
	/**
	 * Creates the bankoutput file and adds a line with the bank account information separated by spaces.
	 * Creates a new blank line at the end.
	 * */
	private void writeAccountInformationOnFile(BankAccount account) throws IOException {
		BufferedWriter writer = null;

		try  {
			writer = new BufferedWriter(new FileWriter("C:\\Term2\\Lab9\\src\\bankoutput.txt", true));
			writer.append(account.getFormattedDisplayForFileWriting()+"\n");
			writer.newLine();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    finally {
	    	writer.close();
	    }
		
	}
	/**
	 * Finds and return the index of an account from its accountNumber
	 * */
	public int findAccount(long accountNumber) {
		int index = -1;
		for (int i = 0; i < accounts.size(); i++) {
			if(accountNumber == accounts.get(i).accountNumber)
				index = i;
		}
		return index;
	}
	/**
	 * Does the monthlyUpdate for all accounts
	 * */
	public void monthlyUpdate() {
		for (int i = 0; i < accounts.size(); i++) {
			accounts.get(i).monthlyAccountUpdate();
		}
	}
	/**
	 *
	 * */
	private long[] accountNumbersInUse() {
		long [] inUse = new long[accounts.size()];
		for (int i = 0; i < accounts.size(); i++) {
			inUse[i] = accounts.get(i).accountNumber; 
		}
		return inUse;
	}
	
	/**
	 * 
	 * */
	public void readRecords() {
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader("C:\\Term2\\Lab9\\src\\bankinput.txt");
			br = new BufferedReader(fr);
	
			String currentLine;
	
			while ((currentLine = br.readLine()) != null) {
				
				String[] info = currentLine.split(" ");
				long accountNumber = Long.parseLong(info[1]);
				String firstName = info[2];
				String lastName = info[3];
				long phoneNumber = Long.parseLong(info[4]);
				String email = info[5];
				double initialBalance = Double.parseDouble(info[6]);
				
				boolean create = true;
				long[] accountsInUse = accountNumbersInUse();
				for (int i = 0; i < accountsInUse.length; i++) {
					if(accountNumber==accountsInUse[i])
						create = false;
				}
				if(create) {
				if(info[0].toUpperCase().equals("S")) {
					double minimumBalance = Double.parseDouble(info[7]);
					double interestRate = Double.parseDouble(info[8]);
					BankAccount account = new SavingsAccount(accountNumber, new Person(firstName, lastName, phoneNumber, email),
							initialBalance, interestRate, minimumBalance);
					accounts.add(account);
				}
				else if(info[0].equals("C")) {

					double fee = Double.parseDouble(info[7]);
					
					BankAccount account = new ChequingAccount(accountNumber, new Person(firstName, lastName, phoneNumber, email),
							initialBalance, fee);
					accounts.add(account);
				}				
			}
			}
		}catch (Exception e){

		}
	}

}

package model;

public class BankSingleton {
	private static Bank bank = new Bank();
	
	private BankSingleton() {
		
	}
	
	public static Bank getBank() {
		return bank;
	}
}

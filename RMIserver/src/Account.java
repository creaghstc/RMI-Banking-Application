import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

//Conor Creagh 13454222
public class Account {
	//variables
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private String username;
	private String password;
	private int accountNo;
	private int balance;
	LocalDate localDate;

	//constructor
	public Account(String Username, String Password, int AccountNo, int Balance){
		username = Username;
		password = Password;
		accountNo = AccountNo;
		balance = Balance;
	}
	
	//setter for username
	public void setUsername(String name){
		username = name;
	}
	//setter for password
	public void setPassword(String pass){
		password = pass;
	}
	//setter for account number
	public void setAccountNo(int number){
		accountNo = number;
	}
	//setter for balance
	public void setBalance(int bal){
		balance = bal;
	}
	//getter for username
	public String getUsername() {
		return username;
	}
	//getter for password
	public String getPassword() {
		return password;
	}
	//deposit method
	public void depositFunds(int amount) {
		balance += amount; //increment balance to amaount parameter
		System.out.println(amount + " Succesfully deposited"); //report
		transactions.add(new Transaction(LocalDate.now(), "Deposit : " + amount )); //log transaction

	}
	//getter account number
	public int getAccountNo() {
		return accountNo;
	}
	//withdraw method
	public void withdrawFunds(int amount) {
		balance -= amount; //decrement amount 
		System.out.println(amount + " Succesfully withdrawn"); //report
		transactions.add(new Transaction(localDate.now(), "Withdraw: " + amount )); //log transaction

	}
	//getter for balance 
	public int getbalance() {
		return balance;
	}
	//search transactions method
	public void searchTransactions(LocalDate from, LocalDate to){
		System.out.println("--------STATEMENT--------");	
		for(Transaction t : transactions){ //loop over transactions
			if(t.getDate().isAfter(from) && t.getDate().isBefore(to)){ //check if is after and before search params
				System.out.println(t.getDate()+ " :" + t.getInfo()); //print t to user
			}
			else{
				System.out.println("No transactions in current date Parameters"); //inform user if no transactions in given window
			}
		}		
	}

	

}

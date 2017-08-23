import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Atm client was run through eclipse while server was run through command prompt

//Conor Creagh 13454222

public class Bank extends UnicastRemoteObject implements BankInterface{
	
	protected Bank() throws RemoteException {	}

	private static List<Account> accounts = new ArrayList<Account>(); //array list of accounts
	
	//sample accounts
	static Account acc1 = new Account("conor", "123", 1, 200);
	static Account acc2 = new Account("john", "pass2", 2, 0);
	static Account acc3 = new Account("david", "yes", 3, 150);
	static Account acc4 = new Account("jim", "maybe", 4, 25);
	
	long sessionID = 0;

	
	
	
	
	//login method
	public long login(String username, String password) throws RemoteException, InvalidLogin {
		for(Account a : accounts){ //loop thorugh accounts
			if(a.getUsername().equals(username) && a.getPassword().equals(password)){ //if username and password match - login
				System.out.println("valid login");
				sessionID  = 1;
			}
		}
		return sessionID; //return session id
	}
	//deposit method
	public void deposit(int accountnum, int amount, long sessionID)
			throws RemoteException, InvalidSession {
		if(sessionID != 0){ //if session id is not 0
			for(Account a : accounts){ //loop over accounts
				if(a.getAccountNo() == accountnum){ //find account number
					a.depositFunds(amount); //call account deposit method 
					
				}
			}
		}
		else{ //if no session throw ....
			throw new InvalidSession();
		}		
	}
	//withdraw method
	public void withdraw(int accountnum, int amount, long sessionID)
			throws RemoteException, InvalidSession {
		if(sessionID != 0){ //check valid session id
			for(Account a : accounts){ //loop over accounts
				if(a.getAccountNo() == accountnum){ //get account
					a.withdrawFunds(amount); //call account withdraw method
					
				}
			}
		}
		else{
			throw new InvalidSession();
		}	
	}
	//inquiry method
	public int inquiry(int accountnum, long sessionID) throws RemoteException,
			InvalidSession {
		
		int balance = 0; //initialize balance
		if(sessionID != 0){ //check valid session
			for(Account a : accounts){ //loop over accounts
				if(a.getAccountNo() == accountnum){ //get account
					balance = a.getbalance(); //call account balance method
					
				}
			}
		}
		else{
			throw new InvalidSession();
		}
		
		System.out.println("The balance of account: " + accountnum + " is " + balance); //return the balance to user
		return balance;
	}
	//statement method
	public void getStatement(int accountnum, LocalDate from, LocalDate to, long sessionID)
			throws RemoteException, InvalidSession {
		if(sessionID != 0){ //check valid session
			for(Account a : accounts){ //loop over accounts
				if(a.getAccountNo() == accountnum){ //get acoount
					a.searchTransactions(from, to); //call search transactions
					
				}
			}
		}
		else{
			throw new InvalidSession();
		}
		
	}
	//main server method
	public static void main(String args[]) throws RemoteException{
		//setup
		BankInterface bank;
		bank = new Bank();
		accounts.add(acc1);
		accounts.add(acc2);
		accounts.add(acc3);
		accounts.add(acc4);
		
		try{
		    LocateRegistry.createRegistry(Integer.valueOf(args[0])); //set port of registry with first argument 
			LocateRegistry.getRegistry().rebind("banking", bank); //rebind
			System.out.println("System is ready"); //tell user system ready 
			
		}
		catch (Exception e){ //tell user server failed and why 
			System.out.println("server failed: " + e);
			
		}
	}
}

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//Conor Creagh 13454222
//Atm client was run through eclipse while server was run through command prompt


public class Atm {
	
	
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException{
		//initialize variables
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //to format dates
		BankInterface bank;
		long sessionID = 1; //so methods dont throw invalid session, actual session system not working
		Registry reg;

	
		try{

			reg = LocateRegistry.getRegistry(args[0], Integer.valueOf(args[1])); // get host and port off of first 2 arguements
			bank = (BankInterface) reg.lookup("banking"); //lookup registry
	
			switch(args[2]){ //switch statements on argument 2
			
			case "login": //assign session id from login method 
				sessionID = bank.login(args[3], args[4]);
				break;
		
			case "deposit": //call deposit method
				bank.deposit(Integer.valueOf(args[3]), Integer.valueOf(args[4]), sessionID); //use arguments for parameters
				break;
				
			case "withdraw": //call withdraw method
				bank.withdraw(Integer.valueOf(args[3]), Integer.valueOf(args[4]), sessionID); //use arguments for parameters
				break;
				
			case "inquiry": //call inquiry method
				bank.inquiry(Integer.valueOf(args[3]), sessionID); //use arguments for parameters
				break;
				
			case "getStatement":
				//format arguments for integer and LocalDate
				bank.getStatement(Integer.valueOf(args[3]), LocalDate.parse(args[4], formatter), LocalDate.parse(args[5], formatter), sessionID);
			
			default:
				System.out.println("enter operation");
				break;
			}
		}
		catch( Exception e){ //catch exception
			System.out.print(e); //print exception thrown
		}
	}
	
}

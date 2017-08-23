import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

//Conor Creagh 13454222

public interface BankInterface extends Remote{
	
	public long login(String username, String password) throws RemoteException, InvalidLogin;

	public void deposit(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

	public int inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession;

	public void getStatement(int accountnum, LocalDate from, LocalDate to, long sessionID) throws RemoteException, InvalidSession;

}

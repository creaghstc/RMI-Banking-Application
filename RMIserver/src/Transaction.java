import java.time.LocalDate;
import java.util.Date;


//Conor Creagh 13454222

public class Transaction {
	//variables
	private LocalDate date;
	private String information;
	//constructor
	public Transaction(LocalDate localDate, String info){
		date = localDate;
		information = info;
		
	}
	//getter for date
	public LocalDate getDate() {
		return date;
	}
	public String getInfo() {
		return information;
	}

}

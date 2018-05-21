import java.time.*;


public class GetSystemDateTime {
	
	public Object[] outputDate() {
	
		LocalDate inputDate = LocalDate.now();
		LocalTime inputTime = LocalTime.now();
		Object [] datetime = {inputDate, inputTime};
		return datetime;
		
	}
	
}

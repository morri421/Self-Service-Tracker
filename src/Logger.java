public class Logger {
	
	public void createLog (String buttonclicked) {
		
		GetSystemDateTime systemDate = new GetSystemDateTime();
		Object[] logEntryDate = systemDate.outputDate();
		ToFile temp = new ToFile();
		temp.writeToLog(logEntryDate, buttonclicked);
		
	}
	
}
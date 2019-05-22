package com.tr.logger;

/**
 * Log class takes the value of the button clicked and calls class to write to
 * file
 *
 */
public class Logger {

	public void createLog(String buttonclicked) {

		SystemDateTime systemDate = new SystemDateTime(); 
		Object[] logEntryDate = systemDate.outputDate();
		FileWriteLogger fileLogger = new FileWriteLogger();
		fileLogger.writeToLog(logEntryDate, buttonclicked);

	}

}
package com.tr.logger;

import java.time.*;

/**
 * Gets local data and time stamp and returns for logger
 *
 */
public class SystemDateTime {

	public Object[] outputDate() {

		LocalDate dateInput = LocalDate.now();
		LocalTime timeInput = LocalTime.now();
		Object[] datetime = { dateInput, timeInput };
		return datetime;

	}

}

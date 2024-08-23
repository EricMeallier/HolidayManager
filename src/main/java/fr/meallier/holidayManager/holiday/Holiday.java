package fr.meallier.holidayManager.holiday;

import fr.meallier.holidayManager.holiday.algorithm.HolidayAlgorithm;

import java.time.LocalDate;
import java.time.MonthDay;

public class Holiday {
	String description;
	HolidayType holidayType;
	HolidayAlgorithm holidayAlgorithm;
	MonthDay holidayDate;

	private Holiday(String description, HolidayType holidayType, HolidayAlgorithm holidayAlgorithm, MonthDay holidayDate) {
		this.description = description;
		this.holidayType = holidayType;
		this.holidayAlgorithm = holidayAlgorithm;
		this.holidayDate = holidayDate;
	}
	

	public static Holiday buildFixedHoliday(String description, MonthDay holidayDate) {
		return new Holiday(description, HolidayType.FIXED, null, holidayDate);
	}
	
	public static Holiday buildComputedHoliday(String description, HolidayAlgorithm holidayAlgorithm) {
		return new Holiday(description, HolidayType.COMPUTED, holidayAlgorithm, null);
	}
	
	public LocalDate compute(int year) {
		return switch (this.holidayType) {
			case FIXED -> this.holidayDate.atYear(year);
			case COMPUTED -> this.holidayAlgorithm.getValue().compute(year);
		};
	}
}

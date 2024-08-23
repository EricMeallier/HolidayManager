package fr.meallier.holiday.holiday;

import fr.meallier.holiday.holiday.algorithm.HolidayAlgorithm;

import java.time.LocalDate;
import java.time.MonthDay;

public class Holiday {
	String description;
	HolidayType holidayType;
	HolidayAlgorithm holidayAlgorithm;
	MonthDay holidayDate;

	private Holiday(String description, HolidayType holidayType, HolidayAlgorithm holidayAlgorithm) {
		super();
		this.description = description;
		this.holidayType = holidayType;
		this.holidayAlgorithm = holidayAlgorithm;
	}
	
	private Holiday(String description, HolidayType holidayType, MonthDay holidayDate) {
		super();
		this.description = description;
		this.holidayType = holidayType;
		this.holidayDate = holidayDate;
	}
	
	public static Holiday buildFixedHoliday(String description, MonthDay holidayDate) {
		return new Holiday(description, HolidayType.FIXED, holidayDate);
	}
	
	public static Holiday buildComputedHoliday(String description, HolidayAlgorithm holidayAlgorithm) {
		return new Holiday(description, HolidayType.COMPUTED, holidayAlgorithm);
	}
	
	public LocalDate compute(int year) {
		return switch (this.holidayType) {
			case FIXED -> this.holidayDate.atYear(year);
			case COMPUTED -> this.holidayAlgorithm.getValue().compute(year);
		};
	}
}

package fr.meallier.holiday.holiday;

import java.time.LocalDate;
import java.time.MonthDay;

import fr.meallier.holiday.holiday.algorithm.HolidayAlgorithm;

public class Holiday {
	long id;
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HolidayType getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(HolidayType holidayType) {
		this.holidayType = holidayType;
	}
	public HolidayAlgorithm getHolidayAlgorithm() {
		return holidayAlgorithm;
	}
	public void setHolidayAlgorithm(HolidayAlgorithm holidayAlgorithm) {
		this.holidayAlgorithm = holidayAlgorithm;
	}

	public LocalDate compute(int year) {
		switch (this.holidayType) {
			case FIXED:
				return this.holidayDate.atYear(year);
			case COMPUTED:
				return this.holidayAlgorithm.getValue().compute(year);
			default:
				throw new IllegalStateException("Unexpected value: " + this.holidayType);
		}
	}
}

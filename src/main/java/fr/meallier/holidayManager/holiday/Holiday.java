package fr.meallier.holidayManager.holiday;

import fr.meallier.holidayManager.holiday.algorithm.HolidayAlgorithm;
import fr.meallier.holidayManager.persistence.MonthDayConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.UUID;

@Entity
public class Holiday {

	@Id()
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;
	@Column
	String description;
	@Column
	HolidayType holidayType;
	@Column
	HolidayAlgorithm holidayAlgorithm;
	@Column
	@Convert(converter = MonthDayConverter.class)
	MonthDay holidayDate;

	public Holiday() {
	}

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


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public MonthDay getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(MonthDay holidayDate) {
		this.holidayDate = holidayDate;
	}
}

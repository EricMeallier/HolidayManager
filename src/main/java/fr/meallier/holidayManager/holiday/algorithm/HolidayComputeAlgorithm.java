package fr.meallier.holidayManager.holiday.algorithm;

import java.time.LocalDate;

public interface HolidayComputeAlgorithm {
	
	LocalDate compute(int year);
}

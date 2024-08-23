package fr.meallier.holidayManager.dayoff.algorithm.instance;

import fr.meallier.holidayManager.dayoff.algorithm.DayOffComputeAlgorithm;

import java.time.LocalDate;
import java.time.YearMonth;

public class LastDayOfMonthAlgorithm implements DayOffComputeAlgorithm {

	@Override
	public LocalDate compute(LocalDate targetDate) {
		return YearMonth.of(targetDate.getYear(),targetDate.getMonth()).atEndOfMonth();
	}
}

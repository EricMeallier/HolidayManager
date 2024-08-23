package fr.meallier.holiday.dayoff.algorithm.instance;

import fr.meallier.holiday.dayoff.algorithm.DayOffComputeAlgorithm;

import java.time.LocalDate;
import java.time.YearMonth;

public class LastDayOfMonthAlgorithm implements DayOffComputeAlgorithm {

	@Override
	public LocalDate compute(LocalDate targetDate) {
		return YearMonth.of(targetDate.getYear(),targetDate.getMonth()).atEndOfMonth();
	}
}

package fr.meallier.holiday.off.algorithm.instance;

import fr.meallier.holiday.off.algorithm.DayOffAlgorithm;
import fr.meallier.holiday.off.algorithm.DayOffComputeAlgorithm;

import java.time.LocalDate;
import java.time.YearMonth;

public class LastDayOfMonthAlgorithm implements DayOffComputeAlgorithm {

	@Override
	public LocalDate compute(LocalDate targetDate) {
		return YearMonth.of(targetDate.getYear(),targetDate.getMonth()).atEndOfMonth();
	}
}

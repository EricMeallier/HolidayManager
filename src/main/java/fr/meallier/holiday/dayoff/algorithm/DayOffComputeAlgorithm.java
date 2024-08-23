package fr.meallier.holiday.dayoff.algorithm;

import java.time.LocalDate;

public interface DayOffComputeAlgorithm {
	
	LocalDate compute(LocalDate targetDate);
}

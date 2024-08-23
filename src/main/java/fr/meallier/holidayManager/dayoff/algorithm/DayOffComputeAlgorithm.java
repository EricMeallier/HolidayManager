package fr.meallier.holidayManager.dayoff.algorithm;

import java.time.LocalDate;

public interface DayOffComputeAlgorithm {
	
	LocalDate compute(LocalDate targetDate);
}

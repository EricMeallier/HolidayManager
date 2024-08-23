package fr.meallier.holiday.off.algorithm;

import java.time.LocalDate;

public interface DayOffComputeAlgorithm {
	
	LocalDate compute(LocalDate targetDate);
}

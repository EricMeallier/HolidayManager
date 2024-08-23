package fr.meallier.holiday.off.algorithm;

import fr.meallier.holiday.holiday.algorithm.instance.*;
import fr.meallier.holiday.off.algorithm.instance.LastDayOfMonthAlgorithm;

public enum DayOffAlgorithm {
	
	LASTDAYOFMONTH(new LastDayOfMonthAlgorithm());

	private final DayOffComputeAlgorithm computeAlgorithm;
	
	private DayOffAlgorithm(DayOffComputeAlgorithm computeAlgorithm) {
		this.computeAlgorithm = computeAlgorithm;
	}
	
	public DayOffComputeAlgorithm getValue() {
		return computeAlgorithm;
	}
}

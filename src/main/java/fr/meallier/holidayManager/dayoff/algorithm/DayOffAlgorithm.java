package fr.meallier.holidayManager.dayoff.algorithm;

import fr.meallier.holidayManager.dayoff.algorithm.instance.LastDayOfMonthAlgorithm;

public enum DayOffAlgorithm {
	
	LASTDAYOFMONTH(new LastDayOfMonthAlgorithm());

	private final DayOffComputeAlgorithm computeAlgorithm;

	DayOffAlgorithm(DayOffComputeAlgorithm computeAlgorithm) {
		this.computeAlgorithm = computeAlgorithm;
	}
	
	public DayOffComputeAlgorithm getValue() {
		return computeAlgorithm;
	}
}

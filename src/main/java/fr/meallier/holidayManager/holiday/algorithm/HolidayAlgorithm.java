package fr.meallier.holidayManager.holiday.algorithm;

import fr.meallier.holidayManager.holiday.algorithm.instance.*;

public enum HolidayAlgorithm {
	
	EASTER(new EasterAlgorithm()),
	PENTECOTE(new PentecoteAlgorithm()), 
	ASCENT(new AscentAlgorithm()),
	GOODFRIDAY(new GoodFridayAlgorithm()),
	MIDLENT(new MidLentAlgorithm());
	
	private final HolidayComputeAlgorithm computeAlgorithm;

	HolidayAlgorithm(HolidayComputeAlgorithm computeAlgorithm) {
		this.computeAlgorithm = computeAlgorithm;
	}
	
	public HolidayComputeAlgorithm getValue() {
		return computeAlgorithm;
	}
}

package fr.meallier.holiday.holiday.algorithm;

import fr.meallier.holiday.holiday.algorithm.instance.AscentAlgorithm;
import fr.meallier.holiday.holiday.algorithm.instance.EasterAlgorithm;
import fr.meallier.holiday.holiday.algorithm.instance.GoodFridayAlgorithm;
import fr.meallier.holiday.holiday.algorithm.instance.MidLentAlgorithm;
import fr.meallier.holiday.holiday.algorithm.instance.PentecoteAlgorithm;

public enum HolidayAlgorithm {
	
	EASTER(new EasterAlgorithm()),
	PENTECOTE(new PentecoteAlgorithm()), 
	ASCENT(new AscentAlgorithm()),
	GOODFRIDAY(new GoodFridayAlgorithm()),
	MIDLENT(new MidLentAlgorithm());
	
	private final HolidayComputeAlgorithm computeAlgorithm;
	
	private HolidayAlgorithm(HolidayComputeAlgorithm computeAlgorithm) {
		this.computeAlgorithm = computeAlgorithm;
	}
	
	public HolidayComputeAlgorithm getValue() {
		return computeAlgorithm;
	}
}

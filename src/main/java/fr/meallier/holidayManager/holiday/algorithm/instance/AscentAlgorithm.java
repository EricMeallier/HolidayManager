package fr.meallier.holidayManager.holiday.algorithm.instance;

import java.time.LocalDate;

public class AscentAlgorithm extends EasterAlgorithm {

	@Override
	public LocalDate compute(int year) {

		return super.compute(year).plusDays(38);
	}

}

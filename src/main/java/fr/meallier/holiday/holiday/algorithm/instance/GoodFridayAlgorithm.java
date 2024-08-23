package fr.meallier.holiday.holiday.algorithm.instance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GoodFridayAlgorithm extends EasterAlgorithm {

	@Override
	public LocalDate compute(int year) {
		return super.compute(year).minus(3,ChronoUnit.DAYS);
	}

}

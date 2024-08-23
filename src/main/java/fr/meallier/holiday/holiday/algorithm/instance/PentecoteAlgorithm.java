package fr.meallier.holiday.holiday.algorithm.instance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PentecoteAlgorithm extends EasterAlgorithm {

	@Override
	public LocalDate compute(int year) {
		
		return super.compute(year).plus(49, ChronoUnit.DAYS);
	}

}

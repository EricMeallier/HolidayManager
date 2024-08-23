package fr.meallier.holiday.holiday.algorithm.instance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AscentAlgorithm extends EasterAlgorithm {

	@Override
	public LocalDate compute(int year) {
		
		return super.compute(year).plus(38, ChronoUnit.DAYS);
	}

}

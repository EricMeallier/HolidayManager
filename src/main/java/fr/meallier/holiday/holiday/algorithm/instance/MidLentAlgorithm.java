package fr.meallier.holiday.holiday.algorithm.instance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MidLentAlgorithm extends EasterAlgorithm {

	@Override
	public LocalDate compute(int year) {
		return super.compute(year).minus(25,ChronoUnit.DAYS);
	}

}

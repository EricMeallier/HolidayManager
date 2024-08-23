package fr.meallier.holiday;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

import fr.meallier.holiday.holiday.Holiday;
import fr.meallier.holiday.holiday.algorithm.HolidayAlgorithm;


public class Entrypoint {

	private static void main(String[] args) {
		
		List<Holiday> holidayLanguedoc = new ArrayList<>();
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Nouvel An", MonthDay.of(Month.JANUARY, 1) ));
		holidayLanguedoc.add(Holiday.buildComputedHoliday("Paques", HolidayAlgorithm.EASTER ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Fete Travail", MonthDay.of(Month.MAY, 1) ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Armistice 1945", MonthDay.of(Month.MAY, 8) ));
		holidayLanguedoc.add(Holiday.buildComputedHoliday("Ascencion", HolidayAlgorithm.ASCENT ));
		holidayLanguedoc.add(Holiday.buildComputedHoliday("Pentecote", HolidayAlgorithm.PENTECOTE ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Fete nationale", MonthDay.of(Month.JULY, 14) ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Assomption", MonthDay.of(Month.AUGUST, 15) ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Toussaint", MonthDay.of(Month.NOVEMBER, 1) ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Armistice 1918", MonthDay.of(Month.NOVEMBER, 11) ));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Noel", MonthDay.of(Month.DECEMBER, 25) ));

		Holiday.buildFixedHoliday("Abolition esclavage Mayotte", MonthDay.of(Month.APRIL, 27));
		Holiday.buildFixedHoliday("Abolition esclavage Martinique ", MonthDay.of(Month.MAY, 22));
		Holiday.buildFixedHoliday("Abolition esclavage Guadeloupe", MonthDay.of(Month.MAY, 27));
		Holiday.buildFixedHoliday("Abolition esclavage Saint-Martin", MonthDay.of(Month.MAY, 28));
		Holiday.buildFixedHoliday("Abolition esclavage Guyane", MonthDay.of(Month.JUNE, 10));
		Holiday.buildFixedHoliday("Abolition esclavage Saint-Barthelemy", MonthDay.of(Month.OCTOBER, 9));
		Holiday.buildFixedHoliday("Abolition esclavage La Reunion", MonthDay.of(Month.DECEMBER,  20));
		Holiday.buildComputedHoliday("Vendredi saint", HolidayAlgorithm.GOODFRIDAY);
		Holiday.buildFixedHoliday("2nd jour Noel (Moselle)", MonthDay.of(Month.DECEMBER,  26));
		Holiday.buildComputedHoliday("Jeudi mi-careme (Guadeloupe)", HolidayAlgorithm.MIDLENT);
		Holiday.buildFixedHoliday("Arrivee Evangile (Polynesie)", MonthDay.of(Month.MARCH, 5));
		Holiday.buildFixedHoliday("Fete autonomie (Polynesie)", MonthDay.of(Month.JUNE, 29));
		Holiday.buildFixedHoliday("Fete territoire (Wallis Futuna)", MonthDay.of(Month.JULY, 29));
		Holiday.buildFixedHoliday("Saint Pierre Chanel (Wallis Futuna)", MonthDay.of(Month.APRIL, 28));
		
		int year=2024;
		System.out.println("Pour l'annee " + year + ":");
		for (Holiday h: holidayLanguedoc) {
			LocalDate l = h.compute(year);
			System.out.println("\t" + h.getDescription() + ": " + l.getDayOfWeek() + " " + l);
		}
	}

}

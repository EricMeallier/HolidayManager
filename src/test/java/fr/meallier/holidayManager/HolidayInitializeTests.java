package fr.meallier.holidayManager;

import fr.meallier.holidayManager.holiday.Holiday;
import fr.meallier.holidayManager.holiday.algorithm.HolidayAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HolidayInitializeTests {

	static List<Holiday> holidayLanguedoc;

	static List<Holiday> holidayOther;

	@Test
	void contextLoads() {
	}

	@BeforeAll
	static void initializeHoliday() {
		holidayLanguedoc = new ArrayList<>();
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

		holidayOther = new ArrayList<>();
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage Mayotte", MonthDay.of(Month.APRIL, 27)));
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage Martinique ", MonthDay.of(Month.MAY, 22)));
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage Guadeloupe", MonthDay.of(Month.MAY, 27)));
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage Saint-Martin", MonthDay.of(Month.MAY, 28)));
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage Guyane", MonthDay.of(Month.JUNE, 10)));
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage Saint-Barthelemy", MonthDay.of(Month.OCTOBER, 9)));
		holidayOther.add(Holiday.buildFixedHoliday("Abolition esclavage La Reunion", MonthDay.of(Month.DECEMBER,  20)));
		holidayOther.add(Holiday.buildComputedHoliday("Vendredi saint", HolidayAlgorithm.GOODFRIDAY));
		holidayOther.add(Holiday.buildFixedHoliday("2nd jour Noel (Moselle)", MonthDay.of(Month.DECEMBER,  26)));
		holidayOther.add(Holiday.buildComputedHoliday("Jeudi mi-careme (Guadeloupe)", HolidayAlgorithm.MIDLENT));
		holidayOther.add(Holiday.buildFixedHoliday("Arrivee Evangile (Polynesie)", MonthDay.of(Month.MARCH, 5)));
		holidayOther.add(Holiday.buildFixedHoliday("Fete autonomie (Polynesie)", MonthDay.of(Month.JUNE, 29)));
		holidayOther.add(Holiday.buildFixedHoliday("Fete territoire (Wallis Futuna)", MonthDay.of(Month.JULY, 29)));
		holidayOther.add(Holiday.buildFixedHoliday("Saint Pierre Chanel (Wallis Futuna)", MonthDay.of(Month.APRIL, 28)));
	}
}


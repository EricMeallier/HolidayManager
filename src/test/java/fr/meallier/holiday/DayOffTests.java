package fr.meallier.holiday;

import fr.meallier.holiday.off.DayOff;
import fr.meallier.holiday.off.DayOffRecurrentDateFrequency;
import fr.meallier.holiday.off.algorithm.DayOffAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.chrono.ChronoLocalDate;

@SpringBootTest
class DayOffTests {

	@Test
	void contextLoads() {
	}

	@Test
	void DayOffWeeklyTests() {
		DayOff dayOff = DayOff.buildWeekly("Dimanche", DayOfWeek.SUNDAY);
		Assertions.assertEquals(LocalDate.of(2024,8,25), dayOff.computeNextFrom(LocalDate.of(2024,8,23)));
		Assertions.assertEquals(LocalDate.of(2024,8,25), dayOff.computeNextFrom(LocalDate.of(2024,8,25)));
		Assertions.assertEquals(LocalDate.of(2024,9,1), dayOff.computeNextFrom(LocalDate.of(2024,8,26)));
	}

	@Test
	void ManualDateTests() {
		DayOff dayOff = DayOff.buildManual("FixedDay",LocalDate.of(2024,1,1));
		Assertions.assertNull(dayOff.computeNextFrom(LocalDate.of(2024,8,23)));
		Assertions.assertEquals(LocalDate.of(2024,1,1), dayOff.computeNextFrom(LocalDate.of(2023,8,25)));
		Assertions.assertEquals(LocalDate.of(2024,1,1), dayOff.computeNextFrom(LocalDate.of(2024,1,1)));
	}

	@Test
	void RecurrentDateMonthlyTests() {
		DayOff dayOff = DayOff.buildRecurrentDate("Recurrent Monthly", DayOffRecurrentDateFrequency.MONTHLY, MonthDay.of(Month.JULY,14));
		Assertions.assertEquals(LocalDate.of(2025,7,14), dayOff.computeNextFrom(LocalDate.of(2025,3,25)));
		Assertions.assertEquals(LocalDate.of(2026,7,14), dayOff.computeNextFrom(LocalDate.of(2025,8,25)));
		Assertions.assertEquals(LocalDate.of(2025,7,14), dayOff.computeNextFrom(LocalDate.of(2025,7,14)));
	}

	@Test
	void RecurrentDateQuarterlyTests() {
		DayOff dayOff = DayOff.buildRecurrentDate("Recurrent Quartly", DayOffRecurrentDateFrequency.QUARTERLY, MonthDay.of(Month.JULY,14));
		// TODO
	}

	@Test
	void RecurrentDateHalYearlyTests() {
		DayOff dayOff = DayOff.buildRecurrentDate("Recurrent HalfYearly", DayOffRecurrentDateFrequency.HALFYEARLY, MonthDay.of(Month.JULY,14));
		// TODO
	}


	@Test
	void RecurrentDateYearlyTests() {
		DayOff dayOff = DayOff.buildRecurrentDate("Recurrent Yearly", DayOffRecurrentDateFrequency.YEARLY, MonthDay.of(Month.JULY,14));
		// TODO
	}

	@Test
	void RecurrentDayCountTests() {
		DayOff dayOff = DayOff.buildRecurrentDayCount("Recurrent 100 jours",LocalDate.of(2024,8,23),100 );
		// TODO
	}

	@Test
	void ComputeDayOffTests() {
		DayOff dayOff = DayOff.buildComputeDayOff("dernier jour du mois", DayOffAlgorithm.LASTDAYOFMONTH);
		LocalDate result = dayOff.computeNextFrom(LocalDate.of(2024,8,23));

		Assertions.assertEquals(LocalDate.of(2024,8,31),result);
	}
}


package fr.meallier.holidayManager;

import fr.meallier.holidayManager.holiday.Holiday;
import fr.meallier.holidayManager.holiday.algorithm.HolidayAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;

@SpringBootTest
class HolidayTests {

	@Test
	void contextLoads() {
	}

	@Test
	void EasterTests() {
		Holiday holiday = Holiday.buildComputedHoliday("Paques", HolidayAlgorithm.EASTER );

		Assertions.assertEquals(LocalDate.of(2025,4,21),holiday.compute(2025));
		Assertions.assertEquals(LocalDate.of(2024,4,1),holiday.compute(2024));
		Assertions.assertEquals(LocalDate.of(1991,4,1),holiday.compute(1991));
		Assertions.assertEquals(LocalDate.of(2018,4,2),holiday.compute(2018));
	}

	@Test
	void PentecoteTests() {
		Holiday holiday = Holiday.buildComputedHoliday("Pentecote", HolidayAlgorithm.PENTECOTE );

		Assertions.assertEquals(LocalDate.of(2025,6,9),holiday.compute(2025));
		Assertions.assertEquals(LocalDate.of(2024,5,20),holiday.compute(2024));
		Assertions.assertEquals(LocalDate.of(1991,5,20),holiday.compute(1991));
		Assertions.assertEquals(LocalDate.of(2018,5,21),holiday.compute(2018));
	}

	@Test
	void AscentTests() {
		Holiday holiday = Holiday.buildComputedHoliday("Ascension", HolidayAlgorithm.ASCENT );

		Assertions.assertEquals(LocalDate.of(2025,5,29),holiday.compute(2025));
		Assertions.assertEquals(LocalDate.of(2024,5,9),holiday.compute(2024));
		Assertions.assertEquals(LocalDate.of(1991,5,9),holiday.compute(1991));
		Assertions.assertEquals(LocalDate.of(2018,5,10),holiday.compute(2018));
	}

	@Test
	void NewYearTests() {
		Holiday holiday = Holiday.buildFixedHoliday("Nouvel An", MonthDay.of(Month.JANUARY, 1) );

		Assertions.assertEquals(LocalDate.of(2025,1,1),holiday.compute(2025));
		Assertions.assertEquals(LocalDate.of(2024,1,1),holiday.compute(2024));
		Assertions.assertEquals(LocalDate.of(1991,1,1),holiday.compute(1991));
		Assertions.assertEquals(LocalDate.of(2018,1,1),holiday.compute(2018));
	}}


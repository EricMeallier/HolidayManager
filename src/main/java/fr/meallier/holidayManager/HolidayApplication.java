package fr.meallier.holidayManager;

import fr.meallier.holidayManager.agency.AgencyDefaultStatus;
import fr.meallier.holidayManager.agency.AgencyPlanning;
import fr.meallier.holidayManager.agency.AgencyStatus;
import fr.meallier.holidayManager.dayoff.DayOff;
import fr.meallier.holidayManager.holiday.Holiday;
import fr.meallier.holidayManager.holiday.algorithm.HolidayAlgorithm;
import fr.meallier.holidayManager.persistence.AgencyDefaultStatusDAO;
import fr.meallier.holidayManager.persistence.DayOffDAO;
import fr.meallier.holidayManager.persistence.HolidayDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HolidayApplication { //implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory
			.getLogger(HolidayApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HolidayApplication.class, args);
	}

    final List<Holiday> holidayLanguedoc = new ArrayList<>();
    final List<DayOff> dayoffLanguedoc = new ArrayList<>();

    @Autowired
    HolidayDAO holidayDAO;

    @Autowired
    DayOffDAO dayOffDAO;

    @Autowired
    AgencyDefaultStatusDAO agencyDefaultStatusDAO;

	public void run(String... args) throws Exception {
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Nouvel An", MonthDay.of(Month.JANUARY, 1)));
		holidayLanguedoc.add(Holiday.buildComputedHoliday("Paques", HolidayAlgorithm.EASTER));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Fete Travail", MonthDay.of(Month.MAY, 1)));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Armistice 1945", MonthDay.of(Month.MAY, 8)));
		holidayLanguedoc.add(Holiday.buildComputedHoliday("Ascencion", HolidayAlgorithm.ASCENT));
		holidayLanguedoc.add(Holiday.buildComputedHoliday("Pentecote", HolidayAlgorithm.PENTECOTE));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Fete nationale", MonthDay.of(Month.JULY, 14)));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Assomption", MonthDay.of(Month.AUGUST, 15)));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Toussaint", MonthDay.of(Month.NOVEMBER, 1)));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Armistice 1918", MonthDay.of(Month.NOVEMBER, 11)));
		holidayLanguedoc.add(Holiday.buildFixedHoliday("Noel", MonthDay.of(Month.DECEMBER, 25)));

		dayoffLanguedoc.add(DayOff.buildWeekly("Dimanche", DayOfWeek.SUNDAY));
		dayoffLanguedoc.add(DayOff.buildWeekly("Samedi", DayOfWeek.SATURDAY));

		for (int i = 2020; i < 2050; i++) {
			LOG.info("[{}]:{}", i, TravailleAnnee(i));
		}

        AgencyDefaultStatus[] weeklyDefaultStatus = new AgencyDefaultStatus[7];
        weeklyDefaultStatus[0] = AgencyDefaultStatus.buildStatus(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
        weeklyDefaultStatus[1] = AgencyDefaultStatus.buildStatus(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
        weeklyDefaultStatus[2] = AgencyDefaultStatus.buildStatus(DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(18, 0));
        weeklyDefaultStatus[3] = AgencyDefaultStatus.buildStatus(DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
        weeklyDefaultStatus[4] = AgencyDefaultStatus.buildStatus(DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
        weeklyDefaultStatus[5] = AgencyDefaultStatus.buildStatus(DayOfWeek.SATURDAY, LocalTime.of(9, 30), LocalTime.of(12, 0));
        weeklyDefaultStatus[6] = AgencyDefaultStatus.buildStatusClosed(DayOfWeek.SUNDAY);

        List<AgencyPlanning> agencyDates = new CalendarManager().buildWeekCalendarAgency(weeklyDefaultStatus, 2024, 33, holidayLanguedoc, dayoffLanguedoc);
        for (AgencyPlanning date : agencyDates) {
            if (date.getStatus().equals(AgencyStatus.OPEN)) {
                if (date.getOpeningPeriods().length > 1)
                    LOG.info(date.getDate() + ": " + date.getStatus() + " " + date.getOpeningPeriods()[0] + "-" + date.getClosingPeriods()[0] + " " + date.getOpeningPeriods()[1] + "-" + date.getClosingPeriods()[1]);
                else
                    LOG.info(date.getDate() + ": " + date.getStatus() + " " + date.getOpeningPeriods()[0] + "-" + date.getClosingPeriods()[0]);
            } else {
                LOG.info(date.getDate() + ": " + date.getStatus());
            }
        }

        holidayLanguedoc.forEach(holidayDAO::save);
        dayoffLanguedoc.forEach(dayOffDAO::save);
	}

	private String TravailleAnnee(int year) {
        var listDates = new CalendarManager().buildCalendar(year, holidayLanguedoc, dayoffLanguedoc);
		// calendrier des absences
		for (LocalDate d : listDates) {
			LOG.debug("Date fermeture: {}", d.toString());
		}
		// jour off par mois
		int[] byMonth = new int[12];
		for (LocalDate d : listDates) {
			byMonth[d.getMonth().getValue() - 1]++;
		}
		int nombreJourTravaille = 0;
		for (int month = 0; month < 12; month++) {
			LOG.debug("Nombre Off {}: {}/{}", Month.of(month + 1), byMonth[month], YearMonth.of(year, month + 1).lengthOfMonth());
			nombreJourTravaille = nombreJourTravaille + YearMonth.of(year, month + 1).lengthOfMonth() - byMonth[month];
		}
		int nombreJourAnnee = Year.of(year).length();
		LOG.debug("Nombre jour travailles: {}/{}", nombreJourTravaille, nombreJourAnnee);

		return (nombreJourTravaille - 25 - 12) + "/" + nombreJourAnnee;
	}
}

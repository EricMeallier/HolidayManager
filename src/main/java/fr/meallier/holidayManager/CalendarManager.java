package fr.meallier.holidayManager;

import fr.meallier.holidayManager.agency.AgencyDate;
import fr.meallier.holidayManager.agency.AgencyDefaultStatus;
import fr.meallier.holidayManager.dayoff.DayOff;
import fr.meallier.holidayManager.holiday.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CalendarManager {

    Set<LocalDate> buildCalendar(int year, List<Holiday> holidays, List<DayOff> dayOffs) {
        TreeSet<LocalDate> result = new TreeSet<>(new LocalDateComparator());

        for (Holiday holiday : holidays) {
            result.add(holiday.compute(year));
        }

        for (DayOff dayOff : dayOffs) {
            LocalDate currentDate = LocalDate.of(year, 1, 1);
            do {
                var tempo = dayOff.computeNextFrom(currentDate);
                if (tempo.isBefore(LocalDate.of(year + 1, 1, 1)))
                    result.add(tempo);
                currentDate = currentDate.plusDays(1);
            } while (currentDate.isBefore(LocalDate.of(year + 1, 1, 1)));
        }

        return result.descendingSet();
    }

    List<AgencyDate> buildWeekCalendarAgency(AgencyDefaultStatus[] defaultStatus, int year, long weekNumber, List<Holiday> holidays, List<DayOff> dayOffs) {

        List<AgencyDate> result = new ArrayList<>();

        LocalDate desiredDate = LocalDate.of(year, 1, 1)
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        Set<LocalDate> offDates = buildCalendar(year, holidays, dayOffs);

        for (int dayNumber = 0; dayNumber < 7; dayNumber++) {
            LocalDate targetedDate = desiredDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayNumber + 1)));
            AgencyDate adate = AgencyDate.duplicateFromDefault(defaultStatus[dayNumber], targetedDate);
            if (offDates.contains(targetedDate)) {
                adate = AgencyDate.buildOff(targetedDate);
            }

            result.add(adate);
        }

        return result;
    }
}

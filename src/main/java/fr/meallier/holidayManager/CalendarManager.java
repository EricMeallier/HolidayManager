package fr.meallier.holidayManager;

import fr.meallier.holidayManager.dayoff.DayOff;
import fr.meallier.holidayManager.holiday.Holiday;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CalendarManager {

    Set<LocalDate> getCalendar(int year, List<Holiday> holidays, List<DayOff> dayOffs) {
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
}

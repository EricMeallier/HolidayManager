package fr.meallier.holidayManager.service;

import fr.meallier.holidayManager.holiday.Holiday;
import fr.meallier.holidayManager.holiday.algorithm.HolidayAlgorithm;
import fr.meallier.holidayManager.persistence.HolidayDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    private static final Logger LOG = LoggerFactory
            .getLogger(HolidayServiceImpl.class);

    @Autowired
    HolidayDAO holidayDAO;

    public void fillData() {
        List<Holiday> holidayLanguedoc = new ArrayList<>();

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


        holidayLanguedoc.stream().forEach(holidayDAO::save);
    }

    public void save(Holiday holiday) {
        holidayDAO.save(holiday);
    }

    public List<Holiday> getAll() {
        LOG.info("getAll");
        return new ArrayList<>(holidayDAO.findAll());
    }
}

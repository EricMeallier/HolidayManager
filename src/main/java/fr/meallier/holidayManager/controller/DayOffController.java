package fr.meallier.holidayManager.controller;

import fr.meallier.holidayManager.dayoff.DayOff;
import fr.meallier.holidayManager.dayoff.DayOffRecurrentDateFrequency;
import fr.meallier.holidayManager.dayoff.algorithm.DayOffAlgorithm;
import fr.meallier.holidayManager.persistence.DayOffDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/dayoff")
public class DayOffController {
    private static final Logger LOG = LoggerFactory
            .getLogger(DayOffController.class);

    @Autowired
    DayOffDAO dayOffDAO;

    @GetMapping("/fillData")
    public ResponseEntity<Void> fillData() {
        try {
            LOG.info("FillData of Dayoff");
            List<DayOff> dayoffLanguedoc = new ArrayList<>();
            dayoffLanguedoc.add(DayOff.buildWeekly("Dimanche", DayOfWeek.SUNDAY));
            dayoffLanguedoc.add(DayOff.buildWeekly("Samedi", DayOfWeek.SATURDAY));
            dayoffLanguedoc.add(DayOff.buildRecurrentDate("test recurrent", DayOffRecurrentDateFrequency.QUARTERLY, MonthDay.of(3, 19)));
            dayoffLanguedoc.add(DayOff.buildManual("test manual", LocalDate.of(2026, 3, 19)));
            dayoffLanguedoc.add(DayOff.buildRecurrentDayCount("test day count", LocalDate.of(2024, 8, 29), 37));
            dayoffLanguedoc.add(DayOff.buildComputeDayOff("test compute", DayOffAlgorithm.LASTDAYOFMONTH));
            dayoffLanguedoc.forEach(dayOffDAO::save);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping
    public ResponseEntity<DayOff> saveData(@RequestBody DayOff dayOff) {
        try {
            dayOffDAO.save(dayOff);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DayOff> getData(@PathVariable UUID uuid) {
        try {
            Optional<DayOff> result = dayOffDAO.findById(uuid);
            if (result.isPresent())
                return new ResponseEntity<>(result.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping
    public ResponseEntity<List<DayOff>> getData() {
        try {
            List<DayOff> result = dayOffDAO.findAll();

            if (result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}

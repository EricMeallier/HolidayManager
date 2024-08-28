package fr.meallier.holidayManager.controller;

import fr.meallier.holidayManager.dayoff.DayOff;
import fr.meallier.holidayManager.persistence.DayOffDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
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
            LOG.info("FillData");
            List<DayOff> dayoffLanguedoc = new ArrayList<>();
            dayoffLanguedoc.add(DayOff.buildWeekly("Dimanche", DayOfWeek.SUNDAY));
            dayoffLanguedoc.add(DayOff.buildWeekly("Samedi", DayOfWeek.SATURDAY));
            dayoffLanguedoc.stream().forEach(dayOffDAO::save);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }


    @GetMapping("/{id}")
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
            LOG.info("getData");

            List<DayOff> result = dayOffDAO.findAll();

            if (result.isEmpty()) {
                LOG.info("No data");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                LOG.info("All data: {}", result);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}

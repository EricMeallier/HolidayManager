package fr.meallier.holidayManager.controller;

import fr.meallier.holidayManager.holiday.Holiday;
import fr.meallier.holidayManager.service.HolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
    private static final Logger LOG = LoggerFactory
            .getLogger(HolidayController.class);

    @Autowired
    HolidayService holidayService;

    @GetMapping("/fillData")
    public ResponseEntity<Void> fillData() {
        try {
            LOG.info("FillData");
            holidayService.fillData();
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }


    @PostMapping(value = "/user")
    public ResponseEntity<Holiday> saveData(@RequestBody Holiday holiday) {
        try {
            holidayService.save(holiday);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Holiday> getData(@PathVariable UUID uuid) {
        try {
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping
    public ResponseEntity<List<Holiday>> getData() {
        try {
            LOG.info("getData");

            List<Holiday> result = holidayService.getAll();


            if (result.isEmpty()) {
                LOG.info("No data");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                LOG.info("All data: {}", result);
                return new ResponseEntity<>(holidayService.getAll(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}

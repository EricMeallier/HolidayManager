package fr.meallier.holidayManager.controller;

import fr.meallier.holidayManager.agency.AgencyDefaultStatus;
import fr.meallier.holidayManager.persistence.AgencyDefaultStatusDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agency")
public class AgencyDefaultStatusController {
    private static final Logger LOG = LoggerFactory
            .getLogger(AgencyDefaultStatusController.class);

    @Autowired
    AgencyDefaultStatusDAO agencyDefaultStatusDAO;

    @GetMapping("/fillData")
    public ResponseEntity<Void> fillData() {
        try {
            LOG.info("FillData for agency default");
            AgencyDefaultStatus[] weeklyDefaultStatus = new AgencyDefaultStatus[7];
            weeklyDefaultStatus[0] = AgencyDefaultStatus.buildStatus(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
            weeklyDefaultStatus[1] = AgencyDefaultStatus.buildStatus(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
            weeklyDefaultStatus[2] = AgencyDefaultStatus.buildStatus(DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(18, 0));
            weeklyDefaultStatus[3] = AgencyDefaultStatus.buildStatus(DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
            weeklyDefaultStatus[4] = AgencyDefaultStatus.buildStatus(DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(17, 30));
            weeklyDefaultStatus[5] = AgencyDefaultStatus.buildStatus(DayOfWeek.SATURDAY, LocalTime.of(9, 30), LocalTime.of(12, 0));
            weeklyDefaultStatus[6] = AgencyDefaultStatus.buildStatusClosed(DayOfWeek.SUNDAY);

            Arrays.stream(weeklyDefaultStatus).forEach(agencyDefaultStatusDAO::save);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }


    @PostMapping
    public ResponseEntity<AgencyDefaultStatus> saveData(@RequestBody AgencyDefaultStatus agencyDefaultStatus) {
        try {
            agencyDefaultStatusDAO.save(agencyDefaultStatus);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AgencyDefaultStatus> getData(@PathVariable UUID uuid) {
        try {
            var result = agencyDefaultStatusDAO.findById(uuid);
            if (result.isPresent())
                return new ResponseEntity<>(result.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping
    public ResponseEntity<List<AgencyDefaultStatus>> getData() {
        try {
            List<AgencyDefaultStatus> result = agencyDefaultStatusDAO.findAll();

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
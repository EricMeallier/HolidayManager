package fr.meallier.holidayManager.service;

import fr.meallier.holidayManager.holiday.Holiday;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface HolidayService {

    void fillData();

    void save(Holiday holiday);

    List<Holiday> getAll();

    Holiday getItem(UUID uuid);
}

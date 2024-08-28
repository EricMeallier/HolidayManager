package fr.meallier.holidayManager.service;

import fr.meallier.holidayManager.holiday.Holiday;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HolidayService {

    public void fillData();

    public void save(Holiday holiday);

    public List<Holiday> getAll();
}

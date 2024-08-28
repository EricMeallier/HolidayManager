package fr.meallier.holidayManager.persistence;

import fr.meallier.holidayManager.dayoff.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DayOffDAO extends JpaRepository<DayOff, UUID> {

}

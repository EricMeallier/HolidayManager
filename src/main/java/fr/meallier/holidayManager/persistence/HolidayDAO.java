package fr.meallier.holidayManager.persistence;

import fr.meallier.holidayManager.holiday.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HolidayDAO extends JpaRepository<Holiday, UUID> {

}

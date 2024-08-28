package fr.meallier.holidayManager.persistence;

import fr.meallier.holidayManager.agency.AgencyDefaultStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgencyDefaultStatusDAO extends JpaRepository<AgencyDefaultStatus, UUID> {

}

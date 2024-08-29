package fr.meallier.holidayManager.agency;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.meallier.holidayManager.persistence.DayOfWeekConverter;
import fr.meallier.holidayManager.persistence.LocalTimeArrayConverter;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class AgencyDefaultStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty
    UUID id;
    @Column
    @Convert(converter = DayOfWeekConverter.class)
    DayOfWeek dayOfWeek;
    @Column
    AgencyStatus status;
    @Column
    @Convert(converter = LocalTimeArrayConverter.class)
    LocalTime[] OpeningPeriods;
    @Column
    @Convert(converter = LocalTimeArrayConverter.class)
    LocalTime[] ClosingPeriods;

    public AgencyDefaultStatus() {
    }
    private AgencyDefaultStatus(DayOfWeek day, AgencyStatus status) {
        this.dayOfWeek = day;
        this.status = status;
    }

    public static AgencyDefaultStatus buildStatus(DayOfWeek day, LocalTime openAM, LocalTime closeAM, LocalTime openPM, LocalTime closePM) {
        AgencyDefaultStatus result = new AgencyDefaultStatus(day, AgencyStatus.OPEN);
        LocalTime[] opening = new LocalTime[2];
        LocalTime[] closing = new LocalTime[2];
        opening[0] = openAM;
        opening[1] = openPM;
        closing[0] = closeAM;
        closing[1] = closePM;
        result.setOpeningPeriods(opening);
        result.setClosingPeriods(closing);

        return result;
    }

    public static AgencyDefaultStatus buildStatus(DayOfWeek day, LocalTime open, LocalTime close) {
        AgencyDefaultStatus result = new AgencyDefaultStatus(day, AgencyStatus.OPEN);
        LocalTime[] opening = new LocalTime[1];
        LocalTime[] closing = new LocalTime[1];
        opening[0] = open;
        closing[0] = close;
        result.setOpeningPeriods(opening);
        result.setClosingPeriods(closing);

        return result;
    }

    public static AgencyDefaultStatus buildStatusClosed(DayOfWeek day) {
        return new AgencyDefaultStatus(day, AgencyStatus.CLOSED);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public AgencyStatus getStatus() {
        return status;
    }

    public void setStatus(AgencyStatus status) {
        this.status = status;
    }

    public LocalTime[] getOpeningPeriods() {
        return OpeningPeriods;
    }

    public void setOpeningPeriods(LocalTime[] openingPeriods) {
        OpeningPeriods = openingPeriods;
    }

    public LocalTime[] getClosingPeriods() {
        return ClosingPeriods;
    }

    public void setClosingPeriods(LocalTime[] closingPeriods) {
        ClosingPeriods = closingPeriods;
    }
}

package fr.meallier.holidayManager.agency;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class AgencyDefaultStatus {

    DayOfWeek day;
    AgencyStatus status;


    LocalTime[] OpeningPeriods;
    LocalTime[] ClosingPeriods;

    private AgencyDefaultStatus(DayOfWeek day, AgencyStatus status) {
        this.day = day;
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

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
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

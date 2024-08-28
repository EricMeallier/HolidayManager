package fr.meallier.holidayManager.agency;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgencyPlanning {

    LocalDate date;
    AgencyStatus status;
    LocalTime[] openingPeriods;
    LocalTime[] closingPeriods;


    public static AgencyPlanning duplicateFromDefault(AgencyDefaultStatus defaultStatus, LocalDate date) {
        AgencyPlanning result = new AgencyPlanning();
        result.date = date;
        result.status = defaultStatus.status;
        result.openingPeriods = defaultStatus.OpeningPeriods;
        result.closingPeriods = defaultStatus.ClosingPeriods;

        return result;
    }

    public static AgencyPlanning buildOff(LocalDate date) {
        AgencyPlanning result = new AgencyPlanning();
        result.date = date;
        result.status = AgencyStatus.CLOSED;

        return result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AgencyStatus getStatus() {
        return status;
    }

    public void setStatus(AgencyStatus status) {
        this.status = status;
    }

    public LocalTime[] getOpeningPeriods() {
        return openingPeriods;
    }

    public void setOpeningPeriods(LocalTime[] openingPeriods) {
        this.openingPeriods = openingPeriods;
    }

    public LocalTime[] getClosingPeriods() {
        return closingPeriods;
    }

    public void setClosingPeriods(LocalTime[] closingPeriods) {
        this.closingPeriods = closingPeriods;
    }

}

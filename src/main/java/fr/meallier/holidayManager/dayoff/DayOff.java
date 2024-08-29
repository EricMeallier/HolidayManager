package fr.meallier.holidayManager.dayoff;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.MonthDayDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;
import fr.meallier.holidayManager.dayoff.algorithm.DayOffAlgorithm;
import fr.meallier.holidayManager.persistence.DayOfWeekConverter;
import fr.meallier.holidayManager.persistence.MonthDayConverter;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty
    UUID id;
    @Column
    @JsonProperty
    String description;
    @Column
    @JsonProperty
    DayOffType dayOffType;
    @Column
    @JsonProperty
    DayOffRecurrentDateFrequency dayOffRecurrentFrequency;
    @Column
    @Convert(converter = DayOfWeekConverter.class)
    @JsonInclude
    DayOfWeek startingDayOfWeek;
    @Column
    @Convert(converter = MonthDayConverter.class)
    @JsonSerialize(using = MonthDaySerializer.class)
    @JsonDeserialize(using = MonthDayDeserializer.class)
    MonthDay startingMonthDay;
    // Manual date OR starting date
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate fixedDate;
    @JsonProperty
    int dayCount;
    @JsonProperty
    DayOffAlgorithm dayOffAlgorithm;

    public DayOff() {
    }

    private DayOff(String description, DayOffType dayOffType, DayOffRecurrentDateFrequency dayOffRecurrentFrequency, DayOfWeek startingDayOfWeek, MonthDay startingMonthDay, LocalDate fixedDate, int dayCount, DayOffAlgorithm dayOffAlgorithm) {
        this.description = description;
        this.dayOffType = dayOffType;
        this.dayOffRecurrentFrequency = dayOffRecurrentFrequency;
        this.startingDayOfWeek = startingDayOfWeek;
        this.startingMonthDay = startingMonthDay;
        this.fixedDate = fixedDate;
        this.dayCount = dayCount;
        this.dayOffAlgorithm = dayOffAlgorithm;
    }


    public static DayOff buildWeekly(String description, DayOfWeek startingDayOfWeek) {
        return new DayOff(description,DayOffType.WEEKLY,null,startingDayOfWeek,null,null,0,null);
    }

    public static DayOff buildManual(String description, LocalDate manualDate) {
        return new DayOff(description,DayOffType.MANUAL,null,null,null,manualDate,0,null);
    }

    public static DayOff buildRecurrentDate(String description, DayOffRecurrentDateFrequency dayOffRecurrentFrequency, MonthDay startingMonthDay) {
        return new DayOff(description,DayOffType.RECURRENT_DATE,dayOffRecurrentFrequency,null,startingMonthDay,null,0,null);
    }

    public static DayOff buildRecurrentDayCount(String description, LocalDate startingDate, int dayCount) {
        return new DayOff(description,DayOffType.RECURRENT_DAYCOUNT,null,null,null,startingDate,dayCount,null);
    }

    public static DayOff buildComputeDayOff(String description, DayOffAlgorithm dayOffAlgorithm) {
        return new DayOff(description,DayOffType.COMPUTE,null,null,null,null,0,dayOffAlgorithm);
    }

    public LocalDate computeNextFrom(LocalDate targetDate) {
        switch(this.dayOffType) {
            case MANUAL:
                if (targetDate.isBefore(fixedDate) || targetDate.equals(fixedDate))
                    return fixedDate;
                else
                    return null;
            case WEEKLY:
                var delta = startingDayOfWeek.getValue()-targetDate.getDayOfWeek().getValue();
                if (delta<0)
                   delta+=7;
                return targetDate.plusDays(delta);
            case RECURRENT_DATE:
                long monthCount = ChronoUnit.MONTHS.between(startingMonthDay.atYear(targetDate.getYear() - 1), targetDate);
                if (dayOffRecurrentFrequency.equals(DayOffRecurrentDateFrequency.QUARTERLY)) {
                    monthCount = monthCount - monthCount % 3;
                } else if (dayOffRecurrentFrequency.equals(DayOffRecurrentDateFrequency.HALFYEARLY)) {
                    monthCount = monthCount - monthCount % 6;
                } else if (dayOffRecurrentFrequency.equals(DayOffRecurrentDateFrequency.YEARLY)) {
                    monthCount = monthCount - monthCount % 12;
                }
                LocalDate result = startingMonthDay.atYear(targetDate.getYear() - 1).plusMonths(monthCount);
                if (result.isBefore(targetDate)) {
                    return switch (dayOffRecurrentFrequency) {
                        case YEARLY -> result.plusMonths(12);
                        case HALFYEARLY -> result.plusMonths(6);
                        case QUARTERLY -> result.plusMonths(3);
                        case MONTHLY -> result.plusMonths(1);
                    };
                }
                return result;
            case RECURRENT_DAYCOUNT:
                long dayDelta = ChronoUnit.DAYS.between(fixedDate, targetDate);
                dayDelta = dayDelta - dayDelta % dayCount;
                LocalDate resultDay = fixedDate.plusDays(dayDelta);
                if (resultDay.isBefore(targetDate))
                    return resultDay.plusDays(dayCount);
                return resultDay;
            case COMPUTE:
                return dayOffAlgorithm.getValue().compute(targetDate);
            default:
                throw new IllegalStateException("Unexpected value: " + this.dayOffType);
        }
    }

}

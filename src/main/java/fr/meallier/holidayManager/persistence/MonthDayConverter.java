package fr.meallier.holidayManager.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.MonthDay;

@Converter
public class MonthDayConverter implements AttributeConverter<MonthDay, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MonthDay monthDay) {
        if (monthDay != null) {
            return monthDay.getMonthValue() * 100 + monthDay.getDayOfMonth();
        }
        return null;
    }

    @Override
    public MonthDay convertToEntityAttribute(Integer value) {
        if (value != null) {
            return MonthDay.of(value / 100, value % 100);
        }
        return null;
    }
}
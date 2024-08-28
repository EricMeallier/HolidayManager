package fr.meallier.holidayManager.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.DayOfWeek;

@Converter
public class DayOfWeekConverter implements AttributeConverter<DayOfWeek, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DayOfWeek dayOfWeek) {
        if (dayOfWeek != null) {
            return dayOfWeek.getValue();
        }
        return null;
    }

    @Override
    public DayOfWeek convertToEntityAttribute(Integer value) {
        if (value != null) {
            return DayOfWeek.of(value);
        }
        return null;
    }
}
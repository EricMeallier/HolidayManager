package fr.meallier.holidayManager.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Converter
public class LocalTimeArrayConverter implements AttributeConverter<LocalTime[], String> {

    @Override
    public String convertToDatabaseColumn(LocalTime[] value) {
        StringBuilder sb = new StringBuilder();
        if (value != null && value.length != 0) {
            Arrays.stream(value).forEach(v -> sb.append(v.format(DateTimeFormatter.ISO_TIME)).append(','));
            return sb.toString();
        }
        return null;
    }

    @Override
    public LocalTime[] convertToEntityAttribute(String value) {
        if (value != null && !value.isEmpty()) {
            var array = value.split(",");
            var result = new LocalTime[array.length];
            for (int index = 0; index < array.length; index++) {
                result[index] = LocalTime.parse(array[index]);
            }
            return result;
        }
        return null;
    }
}
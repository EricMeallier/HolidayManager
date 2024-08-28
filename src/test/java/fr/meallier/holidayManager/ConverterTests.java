package fr.meallier.holidayManager;

import fr.meallier.holidayManager.persistence.DayOfWeekConverter;
import fr.meallier.holidayManager.persistence.LocalTimeArrayConverter;
import fr.meallier.holidayManager.persistence.MonthDayConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.MonthDay;

@SpringBootTest
class ConverterTests {

    @Test
    void LocalTimeArrayConverterTests() {
        LocalTimeArrayConverter conv = new LocalTimeArrayConverter();

        var input = new LocalTime[2];
        input[0] = LocalTime.of(9, 0);
        input[1] = LocalTime.of(14, 0);
        String resultString = conv.convertToDatabaseColumn(input);
        Assertions.assertEquals("09:00:00,14:00:00,", resultString);

        var resultArray = conv.convertToEntityAttribute(resultString);
        Assertions.assertEquals(2, resultArray.length);
        Assertions.assertEquals(LocalTime.of(9, 0), resultArray[0]);
        Assertions.assertEquals(LocalTime.of(14, 0), resultArray[1]);
    }

    @Test
    void MonthDayConverterTests() {
        var conv = new MonthDayConverter();

        Assertions.assertEquals(1225, conv.convertToDatabaseColumn(MonthDay.of(12, 25)));
        Assertions.assertEquals(1205, conv.convertToDatabaseColumn(MonthDay.of(12, 5)));
        Assertions.assertEquals(105, conv.convertToDatabaseColumn(MonthDay.of(1, 5)));

        Assertions.assertEquals(MonthDay.of(12, 25), conv.convertToEntityAttribute(1225));
        Assertions.assertEquals(MonthDay.of(12, 5), conv.convertToEntityAttribute(1205));
        Assertions.assertEquals(MonthDay.of(1, 5), conv.convertToEntityAttribute(105));
    }

    @Test
    void DayOfWeekConverterTests() {
        var conv = new DayOfWeekConverter();

        Assertions.assertEquals(1, conv.convertToDatabaseColumn(DayOfWeek.MONDAY));
        Assertions.assertEquals(6, conv.convertToDatabaseColumn(DayOfWeek.of(6)));

        Assertions.assertEquals(DayOfWeek.FRIDAY, conv.convertToEntityAttribute(5));
        Assertions.assertEquals(DayOfWeek.WEDNESDAY, conv.convertToEntityAttribute(3));
    }
}
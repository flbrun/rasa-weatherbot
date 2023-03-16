package de.fhdw.webtec.weatherchat.util;



import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Predicate;

/*
------------------------------------------------------------------------------------------------------------------------
Class is a helper class to convert the date format from the JSON response to the needet format.
Also it is used to get the day of the forecast.
------------------------------------------------------------------------------------------------------------------------
 */

public class DayHandler {

    public static final Predicate<String> isDate = input -> {
        try
        {
            LocalDate.parse(input, DateTimeFormatter.ISO_DATE_TIME);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    };
    public static final Predicate<String> isToday = input ->
            Objects.equals(LocalDate.parse(input, DateTimeFormatter.ISO_DATE_TIME), LocalDate.now());



    public static Integer getDay(String time)
    {
        LocalDate localDate = LocalDate.now();
        LocalDate foreCastDate = LocalDate.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        Duration duration = Duration.between(localDate.atStartOfDay(), foreCastDate.atStartOfDay());
        return Math.toIntExact(duration.toDays());
    }

    public static String prettyDate(String time)
    {
        LocalDate date =  LocalDate.parse(time, DateTimeFormatter.ISO_DATE_TIME);

        return date.format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }



}

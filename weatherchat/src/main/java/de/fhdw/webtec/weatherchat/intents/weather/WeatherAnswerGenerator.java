package de.fhdw.webtec.weatherchat.intents.weather;


import de.fhdw.webtec.weatherchat.intents.weather.sentence.WeatherForecastTemplate;
import de.fhdw.webtec.weatherchat.intents.weather.sentence.WeatherTodayTemplate;
import de.fhdw.webtec.weatherchat.util.DayHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Random;

/*
-----------------------------------------------------------------------------------------------------------------------
This class is the generator for the weather answers. It uses the templates from the WeatherTodayTemplate and
the WeatherForecastTemplate.
-----------------------------------------------------------------------------------------------------------------------
 */

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WeatherAnswerGenerator {
    static Random RANDOM = new Random();
    public static String generateTodayWeatherSentence(String location, String temperature) {
        int randomIndex = RANDOM.nextInt(WeatherTodayTemplate.values().length);
       WeatherTodayTemplate weatherTodayTemplate =  WeatherTodayTemplate.values()[randomIndex];

        return weatherTodayTemplate.getTemplate()
                        .replace("{0}", location)
                        .replace("{1}", temperature);
    }
    public static String generateForecastWeatherSentence(String location, String temperature,String date) {
        int randomIndex = RANDOM.nextInt(WeatherForecastTemplate.values().length);
        WeatherForecastTemplate weatherForecastTemplate =  WeatherForecastTemplate.values()[randomIndex];

        return weatherForecastTemplate.getTemplate()
                        .replace("{0}", location)
                        .replace("{1}", temperature)
                        .replace("{2}", DayHandler.prettyDate(date));
    }
}

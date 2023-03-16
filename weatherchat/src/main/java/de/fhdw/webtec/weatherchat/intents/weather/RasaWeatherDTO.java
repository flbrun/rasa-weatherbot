package de.fhdw.webtec.weatherchat.intents.weather;

import de.fhdw.webtec.weatherchat.intents.DTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static de.fhdw.webtec.weatherchat.intents.weather.WeatherResponseKeys.*;

/*
-----------------------------------------------------------------------------------------------------------------------
This class is the DTO for the weather intent. It contains the location, the date, the temperature and the unmapped weather.
-----------------------------------------------------------------------------------------------------------------------
 */

@Data
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RasaWeatherDTO extends DTO {
    String location;
    String date;
    String temperature;
    String unmappedWeather;

    public RasaWeatherDTO(Map<WeatherResponseKeys, String> inputMap)
    {
        this.location = inputMap.get(LOCATION);
        this.date = inputMap.get(DATE);
        this.temperature = inputMap.get(TEMP);
        this.unmappedWeather = inputMap.get(UNMAPPED_WEATHER);
    }
}

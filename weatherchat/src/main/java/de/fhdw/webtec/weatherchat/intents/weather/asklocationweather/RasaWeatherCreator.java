package de.fhdw.webtec.weatherchat.intents.weather.asklocationweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.api.externapi.GeoApifyCall;
import de.fhdw.webtec.weatherchat.api.externapi.OpenWeatherCall;
import de.fhdw.webtec.weatherchat.intents.weather.RasaWeatherDTO;
import de.fhdw.webtec.weatherchat.intents.weather.WeatherResponseKeys;
import de.fhdw.webtec.weatherchat.jsonmapper.CoordinateMapper;
import de.fhdw.webtec.weatherchat.jsonmapper.RasaMapper;
import de.fhdw.webtec.weatherchat.jsonmapper.WeatherMapper;
import de.fhdw.webtec.weatherchat.util.DayHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static de.fhdw.webtec.weatherchat.intents.weather.WeatherResponseKeys.*;

import java.util.*;
/*
------------------------------------------------------------------------------------------------------------------------
  This class handles the ask_location_weather intent from rasa and returns the appropriate response
------------------------------------------------------------------------------------------------------------------------
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RasaWeatherCreator {
    OpenWeatherCall openWeatherCall;
    GeoApifyCall geoApifyCall;
    CoordinateMapper coordinateMapper;
    WeatherMapper weatherMapper;
    RasaMapper rasaMapper;
    Map<WeatherResponseKeys, String> responseMap = new HashMap<>();
    public RasaWeatherDTO askLocationWeatherTime(String entity)
            throws JsonProcessingException
    {
        responseMap.clear();
        locationOrDateChecker(entity);
        if (!responseMap.isEmpty())
        {
            String unmappedCoordinates = geoApifyCall.placeToCoordinates(responseMap.get(LOCATION));
            Map<String, String> coordinates = coordinateMapper.getCoordinateForLocation(unmappedCoordinates);

            if (responseMap.get(DATE) != null) {
                int time = DayHandler.getDay(responseMap.get(DATE));

                if (time <= 0) return null;
                responseMap.put(UNMAPPED_WEATHER,openWeatherCall.getWeather(coordinates.get("lon"), coordinates.get("lat"), time + 1)); // +1 because the api starts at 0 with today
                responseMap.put(TEMP, weatherMapper.getTemperature(responseMap.get(UNMAPPED_WEATHER), Optional.of(time)));
            } else {
                responseMap.put(UNMAPPED_WEATHER, openWeatherCall.getWeather(coordinates.get("lon"), coordinates.get("lat")));
                responseMap.put(TEMP, weatherMapper.getTemperature(responseMap.get(UNMAPPED_WEATHER), Optional.empty()));
            }
        }
        return new RasaWeatherDTO(responseMap);
    }

        private void locationOrDateChecker(String entity)
                throws JsonProcessingException
        {
            String value = rasaMapper.entityValue(entity, "0");

            if (value.isEmpty()) return;
            if (DayHandler.isDate.test(value)) {
                if (!DayHandler.isToday.test(value)) {
                    responseMap.put(DATE, value);
                    if (rasaMapper.entityValue(entity, "1").isEmpty())
                    {
                        responseMap.clear();
                        return;
                    }
                    responseMap.put(LOCATION, rasaMapper.entityValue(entity, "1"));
                } else
                {
                    if (rasaMapper.entityValue(entity, "1").isEmpty())
                    {
                        responseMap.clear();
                        return;
                    }
                    responseMap.put(LOCATION, rasaMapper.entityValue(entity, "1"));
                }
            } else
            {
                responseMap.put(LOCATION, value);
            }
        }
}




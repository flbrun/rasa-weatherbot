package de.fhdw.webtec.weatherchat.intents.weather.followupweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.api.externapi.GeoApifyCall;
import de.fhdw.webtec.weatherchat.api.externapi.OpenWeatherCall;
import de.fhdw.webtec.weatherchat.intents.DTO;
import de.fhdw.webtec.weatherchat.intents.weather.RasaWeatherDTO;
import de.fhdw.webtec.weatherchat.jsonmapper.CoordinateMapper;
import de.fhdw.webtec.weatherchat.jsonmapper.RasaMapper;
import de.fhdw.webtec.weatherchat.jsonmapper.WeatherMapper;
import de.fhdw.webtec.weatherchat.util.DayHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
/*
------------------------------------------------------------------------------------------------------------------------
  This class handles the follow_up_weather intent from rasa and returns the appropriate response
------------------------------------------------------------------------------------------------------------------------
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RasaFollowUpCreator {
    OpenWeatherCall openWeatherCall;
    GeoApifyCall geoApifyCall;
    CoordinateMapper coordinateMapper;
    WeatherMapper weatherMapper;
    RasaMapper rasaMapper;

    public RasaWeatherDTO followUpWeather(String entity, DTO dto)
            throws JsonProcessingException
    {
        RasaWeatherDTO weatherDTO = dto.getWeatherDTO();
        if(weatherDTO == null) return null;

        String unmappedCoordinates = geoApifyCall.placeToCoordinates(weatherDTO.getLocation());
        Map<String, String> coordinates = coordinateMapper.getCoordinateForLocation(unmappedCoordinates);
        String timeValue = timeValue(entity);
        if(timeValue != null)
        {
            int time = DayHandler.getDay(timeValue);

            if (time <= 0) return null;
            weatherDTO.setUnmappedWeather(openWeatherCall.getWeather(coordinates.get("lon"), coordinates.get("lat"), time+1)); // +1 because the api starts at 0 with today
            weatherDTO.setTemperature(weatherMapper.getTemperature(weatherDTO.getUnmappedWeather(), Optional.of(time)));
            weatherDTO.setDate(timeValue);
        } else
        {
            weatherDTO.setUnmappedWeather(openWeatherCall.getWeather(coordinates.get("lon"), coordinates.get("lat")));
            weatherDTO.setTemperature(weatherMapper.getTemperature(weatherDTO.getUnmappedWeather(), Optional.empty()));
            weatherDTO.setDate(null);
        }
        return weatherDTO;
    }

    private String timeValue (String entity)
            throws JsonProcessingException
    {
        String value = rasaMapper.entityValue(entity, "0");

        if (!DayHandler.isToday.test(value))
        {
            return value;
        }
        return null;
    }
}

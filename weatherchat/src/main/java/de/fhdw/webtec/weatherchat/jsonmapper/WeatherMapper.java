package de.fhdw.webtec.weatherchat.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
------------------------------------------------------------------------------------------------------------------------
In this class, the JSON response from the OpenWeather Api is mapped.
------------------------------------------------------------------------------------------------------------------------
 */
@Component
@RequiredArgsConstructor
public class WeatherMapper {

    private final ObjectMapper mapper;

    public String getTemperature(String packedJson, Optional<Integer> day)
            throws JsonProcessingException
    {
        final JsonNode json = mapper.readTree(packedJson);
        return day
                .map(d -> json.get("list").at("/" + day.get()).get("temp").get("day").toString())
                .orElseGet(() -> json.get("main").get("temp").toString());
    }
}

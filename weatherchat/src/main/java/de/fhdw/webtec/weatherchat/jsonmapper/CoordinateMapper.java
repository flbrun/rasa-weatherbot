package de.fhdw.webtec.weatherchat.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;


/*
------------------------------------------------------------------------------------------------------------------------
This Class is responsible for mapping the JSON response from the GeoApify Api to a list of strings.
It is used to get the longitude and latitude from the JSON response.
------------------------------------------------------------------------------------------------------------------------
 */

@Component
@RequiredArgsConstructor
public class CoordinateMapper {
    private final ObjectMapper mapper;

    public Map<String, String> getCoordinateForLocation(String packedJson)
            throws JsonProcessingException
    {
            Map<String, String> results = new HashMap<>();
            final JsonNode json = mapper.readTree(packedJson);
            results.put("lon", json.get("features").at("/0").get("properties").get("lon").toString());
            results.put("lat", json.get("features").at("/0").get("properties").get("lat").toString());

        return results;
    }

}

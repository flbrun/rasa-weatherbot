package de.fhdw.webtec.weatherchat.api.externapi;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.logging.Level;
import java.util.logging.Logger;

/*
------------------------------------------------------------------------------------------------------------------------
This class is responsible for the communication with the GeoApify API.
It is used to get the coordinates for a given location.
A location can only be a city
See https://docs.geoapify.com/api/geocoding for more information
return: a json string with the coordinates and some other information
------------------------------------------------------------------------------------------------------------------------
 */

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeoApifyCall {

    @Value("${geo.api.url}")
    String url;
    @Value("${geo.api.key}")
    String api_key;
    final RestTemplate restTemplate;

    public String placeToCoordinates(String location)
    {
        location = location
                .replace("ü", "ue")
                .replace("ä", "ae")
                .replace("ö", "oe");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("text", location)
                    .queryParam("lang", "en")
                    .queryParam("limit", "10")
                    .queryParam("type", "city")
                    .queryParam("apiKey", api_key);

            Logger.getLogger(getClass().getName())
                    .log(Level.INFO, "Url: {0}", new String[]{builder.toUriString()});

            return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}

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
This class is responsible for the communication with the OpenWeather API.
It is used to get the weather for a given location coordinates.
See https://openweathermap.org/api/one-call-api for more information
return: a json string with the weather information
------------------------------------------------------------------------------------------------------------------------
 */

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenWeatherCall {
    @Value("${weather.api.url}")
    String url;

    @Value("${weather.forecast.url}")
    String forecastUrl;
    @Value("${weather.api.key}")
    String api_key;
    final RestTemplate restTemplate;

    public String getWeather(String lon, String lat)
    {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .queryParam("units", "metric")
                    .queryParam("appid", api_key);

            Logger.getLogger(getClass().getName())
                    .log(Level.INFO, "Url: {0}", new String[]{builder.toUriString()});

            return restTemplate.getForObject(builder.toUriString(), String.class);
    }
    public String getWeather(String lon, String lat, int days)
    {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(forecastUrl)
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .queryParam("cnt", days)
                    .queryParam("units", "metric")
                    .queryParam("appid", api_key);

            Logger.getLogger(getClass().getName())
                    .log(Level.INFO, "Url: {0}", new String[]{builder.toUriString()});

             return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}

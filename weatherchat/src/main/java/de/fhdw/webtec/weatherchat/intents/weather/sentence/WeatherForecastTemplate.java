package de.fhdw.webtec.weatherchat.intents.weather.sentence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum WeatherForecastTemplate {

    WEATHER_FORECAST_TEMPLATE_1("Am {2} wird es in {0} {1}°C."),
    WEATHER_FORECAST_TEMPLATE_2("Am {2} wird es in {1}°C in {0}."),
    WEATHER_FORECAST_TEMPLATE_3("Es wird {1}°C am {2} in {0}."),
    WEATHER_FORECAST_TEMPLATE_4("Die Temeperaturen in {0} betragen {1}°C am {2}.");
    String template;
}

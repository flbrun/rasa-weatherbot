package de.fhdw.webtec.weatherchat.intents.weather.sentence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum WeatherTodayTemplate {
    WEATHER_TODAY_TEMPLATE_1("Die Temeratur heute in {0} beträgt {1}°C."),
    WEATHER_TODAY_TEMPLATE_2("Heute ist es in {0} {1}°C."),
    WEATHER_TODAY_TEMPLATE_3("Heute werden in {0} Temepraturen von {1} °C erreicht."),
    WEATHER_TODAY_TEMPLATE_4("Heute wird das Wetter in {0} {1}°C."),
    WEATHER_TODAY_TEMPLATE_5("Aktuell ist es in {0} {1}°C."),
    WEATHER_TODAY_TEMPLATE_6("Die Temperaturen betragen aktuell {1}°C in {0}.");
    String template;
}

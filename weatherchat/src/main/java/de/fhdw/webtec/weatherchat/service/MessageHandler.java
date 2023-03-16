package de.fhdw.webtec.weatherchat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.api.rasa.RasaCall;
import de.fhdw.webtec.weatherchat.intents.DTO;
import de.fhdw.webtec.weatherchat.intents.weather.asklocationweather.RasaWeatherCreator;
import de.fhdw.webtec.weatherchat.intents.defaults.RasaDefaultCreator;
import de.fhdw.webtec.weatherchat.intents.weather.followupweather.RasaFollowUpCreator;
import de.fhdw.webtec.weatherchat.jsonmapper.MessageToFrontendMapper;
import de.fhdw.webtec.weatherchat.jsonmapper.RasaMapper;
import de.fhdw.webtec.weatherchat.intents.weather.WeatherAnswerGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;


import java.util.logging.Level;
import java.util.logging.Logger;

import static de.fhdw.webtec.weatherchat.intents.RasaResponseKeys.TEXT;

/*
------------------------------------------------------------------------------------------------------------------------
  This class handles the possible weather intents from rasa and returns the appropriate response
------------------------------------------------------------------------------------------------------------------------
 */

@Service
@RequiredArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class MessageHandler {

    final RasaCall rasaCall;
    final RasaMapper rasaMapper;
    final RasaWeatherCreator rasaWeatherCreator;
    final RasaFollowUpCreator rasaFollowUpCreator;
    final RasaDefaultCreator rasaDefaultCreator;
    DTO dto;


    public String frontendInputHandler(String incomingMessage , WebSocketSession webSocketSession) throws JsonProcessingException {

        String entity = rasaCall.getAnswerFromRasa(TEXT, incomingMessage);
        String intent = rasaMapper.getIntent(entity);

        Logger.getLogger(getClass().getName())
                .log(Level.INFO, "Request: {0}, Intent: {1}", new String[]{incomingMessage, intent});

        switch (intent)
            {
                case "ask_location_weather" -> dto = rasaWeatherCreator.askLocationWeatherTime(entity);

                case "follow_up_weather" -> dto = rasaFollowUpCreator.followUpWeather(entity, dto);

                default -> dto = rasaDefaultCreator.defaultFallback(incomingMessage);
            }
            dto.setIntent(intent);
         return intentMappingHandler(dto, webSocketSession);
    }

    private static String intentMappingHandler(DTO dto, WebSocketSession webSocketSession) throws JsonProcessingException {

//        List<String> langList = webSocketSession.getHandshakeHeaders().get("accept-language"); EntryPoint for Mulitlingual support
        String intent = dto.getIntent();
        switch (intent)
        {
            case    "ask_location_weather",
                    "follow_up_weather"->
            {
                return dto.getWeatherDTO() == null ?
                        MessageToFrontendMapper.convertForFrontend("Ich kann nur 5 Tage im Voraus vorhersagen.",""):
                        dto.getWeatherDTO().getTemperature() == null ?
                            MessageToFrontendMapper.convertForFrontend("Hast du einen Ort angegeben ? Ich kann dir sonst das Wetter nicht sagen.",""):
                                dto.getWeatherDTO().getDate() !=null ?
                                        MessageToFrontendMapper.convertForFrontend(
                                                WeatherAnswerGenerator.generateForecastWeatherSentence(
                                                        dto.getWeatherDTO().getLocation(),
                                                        dto.getWeatherDTO().getTemperature(),
                                                        dto.getWeatherDTO().getDate()),
                                                dto.getWeatherDTO().getUnmappedWeather()):
                                        MessageToFrontendMapper.convertForFrontend(
                                                WeatherAnswerGenerator.generateTodayWeatherSentence(
                                                        dto.getWeatherDTO().getLocation(),
                                                        dto.getWeatherDTO().getTemperature()),
                                                dto.getWeatherDTO().getUnmappedWeather());
            }
            //... possible Intents
            default ->
            {
                return MessageToFrontendMapper.convertForFrontend(dto.getDefaultDTO().getText(),dto.getDefaultDTO().getData());
            }
        }
    }


}

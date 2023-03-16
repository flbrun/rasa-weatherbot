package de.fhdw.webtec.weatherchat.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.MapperTemplate;
import de.fhdw.webtec.weatherchat.UnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RasaMapperTest implements UnitTest {

    @Autowired
    RasaMapper rasaMapper;

    @Test
    void GreetingDialogTestNotNull()
            throws JsonProcessingException
    {
        Map<String, String> response = rasaMapper.getTextAnswer(MapperTemplate.RASA_DIALOG_RESPONSE_GREETING.json);
        assert response != null;
    }

    @Test
    void GreetingDialogTestCorrectResponse()
            throws JsonProcessingException
    {
       Map<String, String> response = rasaMapper.getTextAnswer(MapperTemplate.RASA_DIALOG_RESPONSE_GREETING.json);
        assert response.get("text").equals("Guten Tag! Wie geht es dir?");
    }

    @Test
    void SadDialogTestNotNull()
            throws JsonProcessingException
    {
        Map<String, String> results = rasaMapper.getTextAnswer(MapperTemplate.RASA_DIALOG_SAD.json);
        assert results != null;
    }

    @Test
    void SadDialogTestCorrectResponse()
            throws JsonProcessingException
    {
        Map<String, String> expected= Map.of("text","Hier ist eine Kleinigkeit um dich aufzuheitern:","data","\"https://i.imgur.com/nGF1K8f.jpg\"");
        Map<String, String> results = rasaMapper.getTextAnswer(MapperTemplate.RASA_DIALOG_SAD.json);
        assertEquals(expected, results);
    }

    @Test
    void RasaIntentTestNotNull()
            throws JsonProcessingException
    {
        String result = rasaMapper.getIntent(MapperTemplate.RASA_INTENT_AND_ENTITIES_LOCATION_AND_WEATHER_BIELEFELD.json);
        assert result != null;
    }

    @Test
    void RasaIntentTestCorrectResponse()
            throws JsonProcessingException
    {
        String expected = "ask_location_weather_time";
        String result = rasaMapper.getIntent(MapperTemplate.RASA_INTENT_AND_ENTITIES_LOCATION_AND_WEATHER_BIELEFELD.json);
        assert result.equals(expected);
    }

    @Test
    void RasaEntityTimeTestNotNull()
            throws JsonProcessingException
    {
        String result = rasaMapper.entityValue(MapperTemplate.RASA_INTENT_AND_ENTITIES_LOCATION_AND_WEATHER_BIELEFELD.json, "0");
        assert result != null;
    }
    @Test
    void RasaEntityTimeTestCorrectResponse()
            throws JsonProcessingException
    {
        String expected = "2023-01-26T00:00:00.000+01:00";
        String result = rasaMapper.entityValue(MapperTemplate.RASA_INTENT_AND_ENTITIES_LOCATION_AND_WEATHER_BIELEFELD.json, "0");
        assert result.equals(expected);
    }

    @Test
    void RasaEntityAddressTestNotNull()
            throws JsonProcessingException
    {
        String result = rasaMapper.entityValue(MapperTemplate.RASA_INTENT_AND_ENTITIES_LOCATION_AND_WEATHER_BIELEFELD.json, "1");
        assert result != null;
    }
    @Test
    void RasaEntityAddressTestCorrectResponse()
            throws JsonProcessingException
    {
        String expected = "Bielefeld";
        String result = rasaMapper.entityValue(MapperTemplate.RASA_INTENT_AND_ENTITIES_LOCATION_AND_WEATHER_BIELEFELD.json, "1");
        assert result.equals(expected);
    }



}

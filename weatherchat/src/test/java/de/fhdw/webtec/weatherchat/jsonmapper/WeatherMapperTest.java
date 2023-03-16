package de.fhdw.webtec.weatherchat.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.MapperTemplate;
import de.fhdw.webtec.weatherchat.UnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class WeatherMapperTest implements UnitTest {

    @Autowired
    WeatherMapper weatherMapper;

    @Test
    void foreCastTemperatureTestNotNull()
            throws JsonProcessingException
    {
        String result = weatherMapper.getTemperature(MapperTemplate.WEATHER_BIELEFELD_TOMORROW.json, Optional.of(0));
        assert result != null;
    }

    @Test
    void foreCastTemperatureTestCorrectResponse()
            throws JsonProcessingException
    {
        String expected = "4.06";
        String result = weatherMapper.getTemperature(MapperTemplate.WEATHER_BIELEFELD_TOMORROW.json, Optional.of(1));
        assert expected.equals(result);
    }

    @Test
    void getTemperatureTestNotNull()
            throws JsonProcessingException
    {
        String result = weatherMapper.getTemperature(MapperTemplate.WEATHER_BIELEFELD.json, Optional.empty());
        assert result != null;
    }

    @Test
    void getTemperatureTestCoorectResponse()
            throws JsonProcessingException
    {
        String expected = "-1.06";
        String result = weatherMapper.getTemperature(MapperTemplate.WEATHER_BIELEFELD.json, Optional.empty());
        assert expected.equals(result);
    }
}

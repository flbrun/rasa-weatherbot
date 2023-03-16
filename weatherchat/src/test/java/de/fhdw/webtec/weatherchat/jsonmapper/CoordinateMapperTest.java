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
public class CoordinateMapperTest implements UnitTest {

    @Autowired
    CoordinateMapper coordinateMapper;

    @Test
    void getCoordinateForLocationTestNotNull()
            throws JsonProcessingException
    {
        Map<String, String> results = coordinateMapper.getCoordinateForLocation(MapperTemplate.COORDINATES_FOR_MUNICH.json);

        assert results != null;
    }

    @Test
    void getCoordinateForLocationTestCorrectResponse()
            throws JsonProcessingException
    {
        //Munich Coordinates
        Map<String, String> expected = Map.of("lon","11.5753822","lat", "48.1371079");
        Map<String, String> results = coordinateMapper.getCoordinateForLocation(MapperTemplate.COORDINATES_FOR_MUNICH.json);

        assertEquals(expected, results);
    }



}


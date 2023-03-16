package de.fhdw.webtec.weatherchat.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.fhdw.webtec.weatherchat.intents.RasaResponseKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
------------------------------------------------------------------------------------------------------------------------
In this class, the JSON response from the Rasa Api is mapped.
------------------------------------------------------------------------------------------------------------------------
 */

@Component
@RequiredArgsConstructor
public class RasaMapper {

    private final ObjectMapper mapper;

    /*
------------------------------------------------------------------------------------------------------------------------
This Method is responsible for mapping the JSON response from the Rasa NLU server to a list of strings.
The first string is the text answer from the Rasa NLU server.
The second string is the image answer from the Rasa NLU server.
If there is no image answer, the second string is "".
------------------------------------------------------------------------------------------------------------------------
     */
    public Map<String, String> getTextAnswer(String packedJson)
            throws JsonProcessingException
    {
        Map<String, String>response = new HashMap<>();

        final JsonNode json = mapper.readTree(packedJson);

        response.put("text", json.at("/0").get("text").textValue());
        response.put("data", json.at("/1").get("image") == null ? "" : json.at("/1").get("image").toString());

        return response;

    }

    public String getIntent(String packedJson)
            throws JsonProcessingException
    {
        final JsonNode json = mapper.readTree(packedJson);

        return json.get("intent").get("name").textValue();
    }

    public String entityValue(String packedJson, String at)
            throws JsonProcessingException
    {
        final JsonNode json = mapper.readTree(packedJson);

        return json.get("entities").at("/"+ at).get("value").textValue();
    }

    public String convertIncomingMessageToRasaReadableJson(RasaResponseKeys rasaResponseKeys, String messageToConvertToJson)
            throws JsonProcessingException
    {
        ObjectNode messageJson = mapper.createObjectNode();

        messageJson.put(rasaResponseKeys.getExpectation(), messageToConvertToJson);

        return mapper.writeValueAsString(messageJson);
    }

}


package de.fhdw.webtec.weatherchat.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
------------------------------------------------------------------------------------------------------------------------
In this class creates the JSON response for the frontend.
via the method convertForFrontend, the text and data are combined to a JSON response.
------------------------------------------------------------------------------------------------------------------------
 */
@Component
@RequiredArgsConstructor

public class MessageToFrontendMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String convertForFrontend(String textValue, String dataValue)
            throws JsonProcessingException {
        final JsonNode jsonNode = mapper.readTree(dataValue);
        ObjectNode json = mapper.createObjectNode();

        json.put("text", textValue);
        json.set("data", jsonNode);

        return mapper.writeValueAsString(json);
    }

}

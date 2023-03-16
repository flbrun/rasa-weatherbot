package de.fhdw.webtec.weatherchat.api.rasa;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.jsonmapper.RasaMapper;
import de.fhdw.webtec.weatherchat.intents.RasaResponseKeys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
------------------------------------------------------------------------------------------------------------------------
This class is responsible for the communication with the Rasa API.
It is used to get the intent and the entities from a given message.
See https://rasa.com/docs/rasa/api/http-api/ for more information
return: a json string with the intent and/or entities
It is also used to communicate with the Rasa for standard communication.
------------------------------------------------------------------------------------------------------------------------
 */

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RasaCall{

    @Value("${rasa.communication.url}")
    String rasaDialogUrl;

    @Value("${rasa.entitymapper.url}")
    String rasaEntityUrl;
    final RestTemplate restTemplate;
    final RasaMapper rasaMapper;

    public String getAnswerFromRasa(RasaResponseKeys rasaResponseKeys, String incomingMessageFromFrontend)
            throws JsonProcessingException
    {
        String request = rasaMapper.convertIncomingMessageToRasaReadableJson(rasaResponseKeys, incomingMessageFromFrontend);

        return rasaResponseKeys.getExpectation().equals("message") ?
                restTemplate.postForObject(rasaDialogUrl,request, String.class ):
                restTemplate.postForObject(rasaEntityUrl,request, String.class );
    }

}

package de.fhdw.webtec.weatherchat.intents.defaults;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fhdw.webtec.weatherchat.api.rasa.RasaCall;
import de.fhdw.webtec.weatherchat.jsonmapper.RasaMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

import static de.fhdw.webtec.weatherchat.intents.defaults.DefaultResponseKeys.*;
import static de.fhdw.webtec.weatherchat.intents.RasaResponseKeys.MESSAGE;
/*
------------------------------------------------------------------------------------------------------------------------
  This class handles the default intent from rasa and returns the appropriate response
------------------------------------------------------------------------------------------------------------------------
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RasaDefaultCreator {

    RasaCall rasaCall;
    RasaMapper rasaMapper;

    public RasaDefaultDTO defaultFallback(String incomingMessage)
            throws JsonProcessingException
    {
        EnumMap<DefaultResponseKeys,String> responseMap = new EnumMap<>(DefaultResponseKeys.class);
        String rasaResponse = rasaCall.getAnswerFromRasa(MESSAGE, incomingMessage);

        responseMap.put(TEXT, rasaMapper.getTextAnswer(rasaResponse).get("text"));
        responseMap.put(DATA, rasaMapper.getTextAnswer(rasaResponse).get("data"));
        return new RasaDefaultDTO(responseMap);
    }


}

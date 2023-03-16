package de.fhdw.webtec.weatherchat.intents.defaults;

import de.fhdw.webtec.weatherchat.intents.DTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static de.fhdw.webtec.weatherchat.intents.defaults.DefaultResponseKeys.*;
/*
-----------------------------------------------------------------------------------------------------------------------
This class is the DTO for the default intent. It contains the text and the data.
-----------------------------------------------------------------------------------------------------------------------
 */
@Data
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RasaDefaultDTO extends DTO {
    String text;
    String data;

    public RasaDefaultDTO(Map<DefaultResponseKeys, String> inputMap)
    {
        this.text = inputMap.get(TEXT);
        this.data = inputMap.get(DATA);
    }
}

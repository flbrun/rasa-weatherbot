package de.fhdw.webtec.weatherchat.intents;



import de.fhdw.webtec.weatherchat.intents.weather.RasaWeatherDTO;
import de.fhdw.webtec.weatherchat.intents.defaults.RasaDefaultDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/*
-----------------------------------------------------------------------------------------------------------------------
This class is the base class for all intents. It contains the intent name and the methods to get the specific DTO.
-----------------------------------------------------------------------------------------------------------------------
 */

@Data
@FieldDefaults( level = AccessLevel.PRIVATE)
public abstract class DTO
{
    String intent;
    public RasaWeatherDTO getWeatherDTO() {return (RasaWeatherDTO) this;}
    public RasaDefaultDTO getDefaultDTO() {return (RasaDefaultDTO) this;}

}

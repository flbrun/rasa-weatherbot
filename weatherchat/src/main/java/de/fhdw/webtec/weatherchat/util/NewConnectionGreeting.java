package de.fhdw.webtec.weatherchat.util;

import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;

/*
-----------------------------------------------------------------------------------------------------------------------
This class is responsible for the greeting message. It is used to greet the user with a different message depending on the time of day.
-----------------------------------------------------------------------------------------------------------------------
 */
public class NewConnectionGreeting
{
    public static String greetingMessage(WebSocketSession webSocketSession)
    {
        //        List<String> langList = webSocketSession.getHandshakeHeaders().get("accept-language"); EntryPoint for Mulitlingual support

        LocalDateTime time = LocalDateTime.now();
        return time.getHour()> 3 && time.getHour() < 11 ? "Guten Morgen, ich bin Rasa Chatbot, stell mir eine Frage." :
                time.getHour() >= 11 && time.getHour() < 18 ? "Guten Tag, ich bin Rasa Chatbot, stell mir eine Frage." :
                        time.getHour() >= 18 && time.getHour() < 22 ? "Guten Abend, ich bin Rasa Chatbot, stell mir eine Frage." : "Hallo, ich bin Rasa Chatbot, stell mir eine Frage.";
    }
}

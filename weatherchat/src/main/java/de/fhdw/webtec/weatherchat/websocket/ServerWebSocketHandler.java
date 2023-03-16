package de.fhdw.webtec.weatherchat.websocket;


import de.fhdw.webtec.weatherchat.jsonmapper.MessageToFrontendMapper;
import de.fhdw.webtec.weatherchat.service.MessageHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static de.fhdw.webtec.weatherchat.util.NewConnectionGreeting.greetingMessage;

/*
------------------------------------------------------------------------------------------------------------------------
This class is responsible for handling the websocket connection between the frontend and the backend.
It is used to send and receive messages from the frontend.
------------------------------------------------------------------------------------------------------------------------
 */

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ServerWebSocketHandler extends TextWebSocketHandler {
    Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    MessageHandler messageHandler;


    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception
    {
        sessions.add(session);
        session.sendMessage(new TextMessage(MessageToFrontendMapper.convertForFrontend(greetingMessage(session), "")));

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception
    {
            String request = message.getPayload();

            String response = messageHandler.frontendInputHandler(request, session);

            session.sendMessage(new TextMessage(response));
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {}
}

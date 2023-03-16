package de.fhdw.webtec.weatherchat.configuration;

import de.fhdw.webtec.weatherchat.websocket.ServerWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


/*
------------------------------------------------------------------------------------------------------------------------
This class is responsible for the configuration of the websocket connection.
It is used to configure the websocket connection between the frontend and the backend.
see https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/socket/config/annotation/WebSocketConfigurer.html
------------------------------------------------------------------------------------------------------------------------
 */

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Value("${origin.path}")
    String origin;

    private final ServerWebSocketHandler serverWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(serverWebSocketHandler, "/chat")
                .setAllowedOriginPatterns(origin);

    }


}



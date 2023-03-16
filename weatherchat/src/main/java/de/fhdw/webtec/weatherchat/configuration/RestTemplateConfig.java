package de.fhdw.webtec.weatherchat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/*
------------------------------------------------------------------------------------------------------------------------
This class is responsible for the configuration of the RestTemplate.
The RestTemplate is used to communicate with the Rasa and the OpenWeather API as well as the GeoApify Api.
It uses the Charset UTF-8 to communicate with the APIs.
see https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
------------------------------------------------------------------------------------------------------------------------
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}

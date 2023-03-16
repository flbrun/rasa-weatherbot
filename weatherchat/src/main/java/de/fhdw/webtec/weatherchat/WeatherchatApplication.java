package de.fhdw.webtec.weatherchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;




/*
------------------------------------------------------------------------------------------------------------------------
This Class is responsible for starting the Spring Boot Application.
The @SpringBootApplication annotation is used to enable Spring Boot auto-configuration and component scanning.
The @EnableAspectJAutoProxy annotation is used to enable AspectJ support.
see: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html
Also lombok is used to reduce boilerplate code in the project.
see: https://projectlombok.org/
------------------------------------------------------------------------------------------------------------------------
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class WeatherchatApplication {

	public static void main(String[] args) {SpringApplication.run(WeatherchatApplication.class, args);}

}

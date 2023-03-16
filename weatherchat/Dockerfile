
FROM maven:3.8.3-openjdk-17-slim as build
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
CMD mkdir ssl
COPY --from=build /app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

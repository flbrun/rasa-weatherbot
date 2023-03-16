
# Stage 1
FROM node:19.5.0-slim as build-step

WORKDIR /app

COPY /weatherchatfrontend/package.json /app
RUN npm install

COPY /weatherchatfrontend /app

RUN npm run build --production

# Stage 2
FROM maven:3.8.3-openjdk-17-slim as build-step-2

WORKDIR /app

COPY /weatherchat /app
COPY --from=build-step /app/dist/weatherchatfrontend /app/src/main/resources/static
RUN mvn clean package

# Stage 3
FROM azul/zulu-openjdk-alpine:17-jre

COPY --from=build-step-2 /app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
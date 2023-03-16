# Stage 1
FROM node:18.14.2-slim as build-step

WORKDIR /app

COPY package.json /app

RUN npm install

COPY . /app

RUN npm run build --production

# Stage 2
FROM nginx:1.23.3-alpine-slim

RUN mv /etc/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf_orig


COPY --from=build-step /app/dist/weatherchatfrontend /usr/share/nginx/html


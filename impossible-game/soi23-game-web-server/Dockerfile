FROM maven:3.9.5-eclipse-temurin-17-focal as builder
COPY . /app/soi23-game-web-server
WORKDIR /app/soi23-game-web-server
RUN mvn clean install

FROM eclipse-temurin:17-jre-focal
COPY app_config_file /opt/app/application.properties
COPY --from=builder /app/soi23-game-web-server/target/soi23-game-web-server-1.0.0-SNAPSHOT.jar /opt/app/app.jar
WORKDIR /opt/app
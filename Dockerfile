# Etapa 1: build con Maven
FROM maven:3.9.6-eclipse-temurin AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: correr solo el JAR
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/mic_push.jar mic_push.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "mic_push.jar"]
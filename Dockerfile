FROM openjdk:21-slim
ARG JAR_FILE=target/push-0.0.1.jar
COPY ${JAR_FILE} mic_push.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "mic_push.jar"]
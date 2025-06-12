FROM openjdk:21-slim
COPY deploy/push-0.0.1.jar mic_push.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "mic_push.jar"]
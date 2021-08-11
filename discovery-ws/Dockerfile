FROM openjdk:8-alpine
VOLUME /tmp
COPY /target/discovery-ws-*.jar discovery-ws.jar
ENTRYPOINT ["java", "-jar", "discovery-ws.jar"]
FROM openjdk:8-alpine
VOLUME /tmp
COPY /target/person-ws-*.jar person-ws.jar
ENTRYPOINT ["java", "-jar", "person-ws.jar"]
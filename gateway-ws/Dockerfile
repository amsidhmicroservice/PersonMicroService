FROM openjdk:8-alpine
VOLUME /tmp
COPY /target/gateway-ws-*.jar gateway-ws.jar
ENTRYPOINT ["java", "-jar", "gateway-ws.jar"]
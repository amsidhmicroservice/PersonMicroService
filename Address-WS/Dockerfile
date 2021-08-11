FROM openjdk:8-alpine
VOLUME /tmp
COPY /target/address-ws-*.jar address-ws.jar
ENTRYPOINT ["java", "-jar", "address-ws.jar"]
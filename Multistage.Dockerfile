FROM openjdk:17

VOLUME /veiculos
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]


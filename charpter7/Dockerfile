FROM alpine:3.16.0
RUN apk add --no-cache java-cacerts openjdk17-jdk

ADD target/*.jar microservice.jar

ENTRYPOINT ["java", "-jar", "microservice.jar"]
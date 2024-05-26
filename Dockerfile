FROM ubuntu:latest
LABEL authors="Akash"
FROM openjdk:17-jdk-alpine
RUN mvn clean package
WORKDIR /app
COPY target/Operations-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT["java","-jar","sb_docker_app.jar"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

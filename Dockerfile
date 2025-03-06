FROM maven:3.8.4-openjdk-17 AS maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:17-alpine

COPY --from=maven-builder app/target/todoapp-stable.jar /todoapp.jar
WORKDIR /app-service

ENTRYPOINT ["java","-jar","/todoapp.jar"]
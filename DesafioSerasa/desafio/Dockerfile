FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY baseA/pom.xml baseA/pom.xml
COPY baseB/pom.xml baseB/pom.xml
COPY baseC/pom.xml baseC/pom.xml

RUN mvn dependency:go-offline

COPY . .

RUN mvn clean install -DskipTests

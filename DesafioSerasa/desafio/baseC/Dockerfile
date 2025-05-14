FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY baseA/pom.xml baseA/pom.xml
COPY baseB/pom.xml baseB/pom.xml
COPY baseC/pom.xml baseC/pom.xml

RUN mvn dependency:go-offline

COPY .. .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/baseC/target/*.jar app.jar
HEALTHCHECK --interval=30s --timeout=3s   CMD curl -f http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]

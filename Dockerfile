FROM maven:3.6.3-openjdk-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn install -DskipTests

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=builder /app/target/MusicCatalog-0.0.1-SNAPSHOT.jar MusicCatalog.jar

ENTRYPOINT ["java","-jar","MusicCatalog.jar"] 
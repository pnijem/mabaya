################### BUILD ##############
FROM maven:3.8.6-eclipse-temurin-11-alpine AS MAVEN_BUILD
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f  /usr/src/app/pom.xml clean package -DskipTests
##################  DEPLOY ########################
FROM bellsoft/liberica-openjdk-alpine:11.0.17


COPY --from=MAVEN_BUILD /usr/src/app/target/proc-trackings-processor.jar  /usr/app/mabaya-ad.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${environment}","-jar", "/usr/app/mabaya-ad.jar"]

# Use openjdk:11-jdk-slim as base container image
ARG MYAPP_IMAGE=openjdk:11-jdk-slim
# Use maven:3.6.3-jdk-11-slim for build
ARG MAVEN_BUILD=maven:3.6.3-jdk-11-slim

#build
FROM $MAVEN_BUILD AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM $MYAPP_IMAGE
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build ${JAR_FILE} /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
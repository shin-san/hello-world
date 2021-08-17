ARG MYAPP_IMAGE

FROM $MYAPP_IMAGE
COPY src /home/src
COPY pom.xml /home/
COPY mvnw /home/mvnw
COPY .mvn /home/.mvn
WORKDIR /home
RUN chmod +x ./mvnw
RUN ./mvnw clean package
COPY ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
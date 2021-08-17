ARG MYAPP_IMAGE

FROM $MYAPP_IMAGE
ARG JAR_FILE=/home/target/*.jar
COPY src /home/src
COPY pom.xml /home/
COPY mvnw /home/mvnw
COPY .mvn /home/.mvn
WORKDIR /home
RUN chmod +x ./mvnw
RUN ./mvnw clean package
COPY ${JAR_FILE} /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
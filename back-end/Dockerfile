FROM openjdk:19-ea-5-jdk-alpine3.16
COPY target/ds-2020-0.0.1-SNAPSHOT.jar /app-service/spring.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app-service/spring.jar"]

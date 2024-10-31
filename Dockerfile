FROM openjdk:8
EXPOSE 8080
ADD target/audiosounds-0.0.2-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/order-service.jar"]

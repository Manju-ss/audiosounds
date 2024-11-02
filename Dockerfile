FROM openjdk:8
EXPOSE 8080
ADD target/audiosounds-0.0.3-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/audiosounds-0.0.3-SNAPSHOT.jar"]

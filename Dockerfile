FROM openjdk:8
EXPOSE 9090
ADD target/hms-server.jar hms-server.jar
ENTRYPOINT ["java","-jar","/hms-server.jar"]
FROM eclipse-temurin:21-jdk
COPY target/task-manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","app.jar"]
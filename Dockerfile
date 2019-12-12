FROM openjdk:11-jre

EXPOSE 8080

ARG JAR_FILE=web/target/web.jar

COPY ${JAR_FILE} /app/app.jar

ENTRYPOINT ["java", "-jar","/app/app.jar"]

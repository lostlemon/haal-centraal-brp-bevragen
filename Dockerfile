FROM maven:3.6.3-jdk-11 as build
WORKDIR /app
COPY . .
RUN mvn -B -DskipTests install

FROM openjdk:11-jre
WORKDIR /app
COPY --from=build /app/web/target/web.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=production","-jar","/app/app.jar"]



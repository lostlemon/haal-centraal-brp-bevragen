FROM alpine/git as clone
ENV url https://github.com/lostlemon/haal-centraal-brp-bevragen
WORKDIR /app
RUN git clone ${url}

FROM maven:3.6.3-jdk-11 as build
ENV project haal-centraal-brp-bevragen
WORKDIR /app
COPY --from=clone /app/${project} /app
RUN mvn -DskipTests install

FROM openjdk:11-jre
WORKDIR /app
COPY --from=build /app/web/target/web.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app/app.jar"]


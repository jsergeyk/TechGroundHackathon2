FROM maven:3.8.5-openjdk-17 as build
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -DskipTests

FROM maven:3.8.5-openjdk-17
COPY --from=build /opt/app/target/*.jar app.jar
ENV PORT 8080
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "-Xmx1024M", "-Dserver.port=${PORT}", "app.jar"]
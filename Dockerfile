# stage 1: build with Maven Wrapper
FROM maven:3.8.7-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw -q -DskipTests package

# stage 2: runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

# Stage 1: build
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy Maven wrapper and project
COPY mvnw ./mvnw
COPY .mvn ./.mvn
COPY pom.xml ./pom.xml
COPY src ./src

# Ensure mvnw is executable
RUN chmod +x mvnw

# Build jar
RUN ./mvnw -q -DskipTests package

# Stage 2: runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

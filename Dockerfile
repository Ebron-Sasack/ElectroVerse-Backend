# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the project
COPY . .

# Build the JAR (skip tests for faster build)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy only the final JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]

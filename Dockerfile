# ================================
# Stage 1: Build the Spring Boot Application
# ================================
FROM eclipse-temurin:17-jdk-focal AS builder

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Make mvnw executable
RUN chmod +x mvnw

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the Spring Boot app (skip test compilation and execution)
RUN ./mvnw clean package -Dmaven.test.skip=true

# ================================
# Stage 2: Run the Spring Boot Application
# ================================
FROM eclipse-temurin:17-jre-focal

# Set working directory
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port (change if needed)
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

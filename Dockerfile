# =============================
# Stage 1: Build the application
# =============================
FROM eclipse-temurin:17-jdk-focal AS builder

# Set working directory inside the build container
WORKDIR /app

# Copy Maven wrapper and configuration files first (to leverage caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cached unless pom.xml changes)
RUN ./mvnw dependency:go-offline -B

# Copy your source code
COPY src src

# Build your Spring Boot JAR file (skip tests for faster deployment)
RUN ./mvnw clean package -DskipTests

# =============================
# Stage 2: Run the application
# =============================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copy only the built jar file from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080 for Render (Render sets PORT environment variable dynamically)
EXPOSE 8080

# Run the Spring Boot application
# Render automatically sets PORT env variable, so pass it dynamically to Spring Boot
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]

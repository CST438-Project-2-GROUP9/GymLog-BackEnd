# Fitness Tracker (Spring Boot) - Docker image
# Purpose:
# - Run the Spring Boot app on Java 21 inside a container
# - Run as a non-root user ("spring") for better security
# - Use an "exploded" Spring Boot fat JAR layout to improve Docker layer caching:
#   dependencies (BOOT-INF/lib) change less often than application classes (BOOT-INF/classes)

# ---- build stage ----
FROM gradle:9.0-jdk21 AS build
WORKDIR /src
COPY . .
RUN gradle bootJar --no-daemon -x test --stacktrace

# ---- runtime stage ----
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
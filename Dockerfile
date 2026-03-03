# Fitness Tracker (Spring Boot) - Docker image
# Purpose:
# - Run the Spring Boot app on Java 21 inside a container
# - Run as a non-root user ("spring") for better security
# - Use an "exploded" Spring Boot fat JAR layout to improve Docker layer caching:
#   dependencies (BOOT-INF/lib) change less often than application classes (BOOT-INF/classes)

FROM eclipse-temurin:21
RUN groupadd --system spring && useradd --system --gid spring spring
USER spring:spring
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","/app:/app/lib/*","com.group9.fitnesstracker.FitnessTrackerApplication"]
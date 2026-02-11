# Stage 1: Build the application
FROM amazoncorretto:21-alpine AS builder

VOLUME /tmp
# No colocar app ni build que se bugea el sfaccold de bancolombia
# Solo al momento de hacer el build
WORKDIR /home/work 

COPY build.gradle settings.gradle main.gradle gradle.properties lombok.config gradlew ./
COPY gradle ./gradle
COPY applications ./applications
COPY domain ./domain
COPY infrastructure ./infrastructure

RUN ./gradlew bootJar --info

# Stage 2: Create the runtime image

FROM eclipse-temurin:21-jre-alpine

VOLUME /tmp
WORKDIR /app

COPY --from=builder /home/work/applications/app-service/build/libs/*.jar docker-example.jar

ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080 

RUN adduser -D -u 1001 appuser
# RUN addgroup -S appgroup && adduser -S appuser -G appgroup
# RUN chown -R appuser:appgroup /app
RUN chown -R appuser:appuser /app

RUN apk add --no-cache curl
USER appuser

HEALTHCHECK --interval=10s --timeout=10s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8000/actuator/health || exit 1

ENTRYPOINT [ "/bin/sh", "-c", "/opt/java/openjdk/bin/java $JAVA_OPTS  -jar ./docker-example.jar" ]

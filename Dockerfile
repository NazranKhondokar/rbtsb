FROM openjdk:17-jdk-alpine AS build
WORKDIR /app
COPY build.gradle .
COPY gradlew .
COPY settings.gradle .
COPY gradle gradle
COPY src src
RUN chmod +x gradlew
RUN ./gradlew bootJar

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app.jar"]


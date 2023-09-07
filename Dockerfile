FROM maven:3.8.5-openjdk-17-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=MAVEN_BUILD /target/booking-reviews-0.0.1-SNAPSHOT.jar /booking-reviews.jar
EXPOSE 8084
CMD ["java", "-jar", "/booking-reviews.jar"]
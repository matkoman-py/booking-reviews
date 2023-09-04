FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/booking-reviews-0.0.1-SNAPSHOT.jar booking-reviews-0.0.1-SNAPSHOT.jar
EXPOSE 8083
CMD ["java", "-jar", "booking-reviews-0.0.1-SNAPSHOT.jar"]
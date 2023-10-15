# Use a base image with Java and Maven installed
FROM maven:3.6.3-openjdk-8 AS build
WORKDIR /app

# Copy the source code and pom.xml
COPY src ./src
COPY pom.xml ./

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use a lightweight image to run the application
FROM openjdk:8-jre-slim
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/COPY target/com-self-sprintboot-learning-eureka-0.0.1-SNAPSHOT.jar /app/eureka-server.jar ./

# Expose the port the application will run on
EXPOSE 8012

# Run the JAR file
CMD ["java", "-jar", "your-game-service.jar"]

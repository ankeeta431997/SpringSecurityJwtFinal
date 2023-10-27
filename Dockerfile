# Use an official OpenJDK 8 image as the base image
FROM openjdk:8

# Expose the port your application will listen on
EXPOSE 8080

# Copy the JAR file to the container
ADD target/devopps-docker.jar devopps-docker.jar

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "/devopps-docker.jar"]


FROM openjdk:21-oracle
VOLUME /tmp

# Accept GITHUB_TOKEN as a build argument
ARG GITHUB_TOKEN

# Set environment variable inside the container
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

COPY target/*.jar  app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
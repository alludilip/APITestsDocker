# Dockerfile to create a containerized environment for running Java tests using Maven and OpenJDK 17 on Debian Bullseye
FROM debian:bullseye-slim

# Install Maven and OpenJDK 17
RUN apt-get update && apt-get install -y \
    maven \
    openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

# Set environment variables
ENV JAVAHoME=/usr/lib/jvm/java-17-openjdk-amd64
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# Set working directory
WORKDIR /app

# Define a volume for persistent data
VOLUME ["/data"]

#Copy source code into container and run tests
COPY . .

# Run tests using Maven
CMD ["mvn","clean","test"]
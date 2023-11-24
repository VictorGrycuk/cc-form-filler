# Use the official AdoptOpenJDK JRE 11 base image
FROM mcr.microsoft.com/playwright/java:v1.39.0-jammy

# Set the working directory
WORKDIR /app

# Install necessary dependencies for Playwright
RUN apt-get update && \
    apt-get install -yq \
    libgbm1 \
    libnss3 \
    libatk-bridge2.0-0 \
    libx11-xcb1 \
    libdrm2 \
    libxkbcommon0 \
    libxcomposite1 \
    libxcursor1 \
    libxdamage1 \
    libxi6 \
    libxtst6 \
    libgtk-3-0 \
    libgdk-pixbuf2.0-0 \
    libpangocairo-1.0-0 \
    libxss1 \
    libappindicator3-1 \
    libdbus-glib-1-2 \
    libglib2.0-0 \
    && rm -rf /var/lib/apt/lists/*

# Copy the fat JAR and Playwright JAR into the container
COPY build/libs/cc-form-filler-1.0.0-fat.jar /app/cc-form-filler-1.0.0-fat.jar
RUN curl -L "https://repo.maven.apache.org/maven2/com/microsoft/playwright/playwright/1.39.0/playwright-1.39.0.jar" -o /app/playwright.jar

# Set the PATH to include Java
ENV PATH="/opt/java/openjdk/bin:${PATH}"

# Expose the port your microservice is running on
EXPOSE 8080

# Run the microservice
CMD ["java", "-cp", "/app/playwright.jar:/app/cc-form-filler-1.0.0-fat.jar", "com.service.infrastructure.http.Application"]
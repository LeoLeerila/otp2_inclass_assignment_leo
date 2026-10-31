FROM maven:latest
WORKDIR /tempconverter
COPY pom.xml .
COPY . /tempconverter
COPY icdb.sql /tempconverter

# Install GUI libraries
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip \
    && rm -rf /var/lib/apt/lists/*

# Download JavaFX SDK
RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-21/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-21 javafx.zip

RUN mvn package
CMD ["java", "-jar", "target/otp2_inclass_assignment_leo-1.0-SNAPSHOT.jar" ]
FROM maven:latest
WORKDIR /tempconverter
COPY pom.xml .
COPY . /tempconverter
RUN mvn package
CMD ["java", "-jar", "target/otp1_inclass1_assignment_leo-1.0-SNAPSHOT.jar" ]
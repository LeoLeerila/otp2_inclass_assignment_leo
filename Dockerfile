FROM maven:latest
WORKDIR /tempconverter
COPY pom.xml .
COPY . /tempconverter
RUN mvn package
CMD ["java", "-jar", "target/otp2_inclass_assignment_leo-1.0-SNAPSHOT.jar" ]
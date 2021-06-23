FROM ubuntu
RUN apt-get update
RUN apt-get install -y maven
WORKDIR /usr/app
COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY src src
ENTRYPOINT ["mvn", "clean", "test"]
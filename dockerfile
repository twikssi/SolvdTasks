FROM ubuntu
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/app/pom.xml
COPY src /usr/app/src
WORKDIR /usr/app/
RUN mvn test package
CMD ["mvn test"]
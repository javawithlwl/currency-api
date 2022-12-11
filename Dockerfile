FROM maven:3.8.6-openjdk-18-slim AS build
RUN mkdir /home/currency
copy . /home/currency
RUN cd /home/currency && mvn package
RUN cp /home/currency/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
FROM maven:3.8.6-openjdk-18-slim AS build
RUN mkdir /home/currency-api
copy . /home/currency-api
RUN cd /home/currency-api && mvn package
RUN cp /home/currency-api/target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/app.jar"]
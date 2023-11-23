FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /inventory-service-app
COPY . .
EXPOSE 8082
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run
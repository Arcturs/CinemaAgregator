FROM maven:3.8.3-openjdk-17-slim as BUILDER
WORKDIR /opt/build/backend
COPY pom.xml /opt/build/backend/
COPY src /opt/build/backend/src/
RUN mvn -f /opt/build/backend/pom.xml clean package -B -DskipTests


FROM openjdk:17-alpine
WORKDIR /opt/app/backend
COPY --from=BUILDER /opt/build/backend/target/*.jar /opt/app/backend/market.jar

RUN apk --no-cache add curl

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar", "/opt/app/backend/backend.jar"]
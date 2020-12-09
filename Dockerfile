FROM openjdk:11-jre-slim
COPY build/libs/api-*.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api.jar"]

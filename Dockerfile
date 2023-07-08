FROM eclipse-temurin:11-alpine
COPY target/*.jar  app/main.jar
CMD ["java","-jar","app/main.jar"]
EXPOSE 8080
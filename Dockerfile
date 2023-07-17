FROM eclipse-temurin:17-alpine
COPY target/*.jar  app/main.jar
CMD ["java","-jar","app/main.jar"]

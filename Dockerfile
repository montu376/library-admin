FROM eclipse-temurin:17-alpine
ADD target/*.jar  app/main.jar
CMD ["java","-jar","app/main.jar"]

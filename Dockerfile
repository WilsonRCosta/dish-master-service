FROM openjdk:23
WORKDIR /app
COPY build/libs/*-SNAPSHOT.jar dish-master-api.jar
EXPOSE 8080
CMD ["java", "-jar", "dish-master-api.jar"]
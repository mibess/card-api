FROM openjdk:17
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
WORKDIR /app
COPY target/*.jar /app/card-api.jar
EXPOSE 8080
CMD ["java", "-jar", "card-api.jar"]

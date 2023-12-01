FROM openjdk:17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/card-api.jar
EXPOSE 8080
CMD ["java", "-jar", "card-api.jar"]

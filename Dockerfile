# Etapa 1: Construcción del JAR con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiamos el proyecto completo al contenedor
COPY . .

# Compila el proyecto sin ejecutar los tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final ligera con Java 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos el .jar desde el contenedor anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

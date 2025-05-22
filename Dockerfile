# Usa una imagen ligera de Java (JDK 17, puedes cambiar si usas otra)
FROM eclipse-temurin:21-jdk-alpine

# Crea el directorio de trabajo
WORKDIR /app

# Copia el jar compilado (asume que el build ya lo generó)
COPY target/mi-app.jar app.jar

# Copia el archivo .env al contenedor (para que dotenv lo encuentre)
##COPY .env .env

# Expón el puerto en el que tu app corre (Spring Boot usa 8080 por defecto)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]

# Usamos una imagen base que contenga Java 20
FROM openjdk:20-jdk-slim

# Establecemos el directorio de trabajo en /app
WORKDIR /app

# Copiamos el archivo pom.xml y los archivos fuente de la aplicación
COPY target/CdOInfoShowService-0.0.1-SNAPSHOT.jar /app

# Comando para arrancar el microservicio (ajusta esto según tu proyecto)
CMD ["java", "-jar", "CdOInfoShowService-0.0.1-SNAPSHOT.jar"]

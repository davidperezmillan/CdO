# Usamos una imagen base que contenga Java 20
FROM openjdk:20-jdk-slim

# Instalamos Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# Establecemos el directorio de trabajo en /app
WORKDIR /app

# Copiamos el archivo pom.xml y los archivos fuente de la aplicación
COPY pom.xml /app
COPY src /app/src

# Ejecutamos maven clean install para construir la aplicación
RUN mvn clean install

# Comando para arrancar el microservicio (ajusta esto según tu proyecto)
CMD ["java", "-jar", "target/CdOInfoShowService-0.0.1-SNAPSHOT.jar"]

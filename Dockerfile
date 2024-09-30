# Usar uma imagem base com JDK 17
FROM openjdk:17-slim

# Copiar o JAR gerado para a imagem
COPY target/restaurante-0.0.1-SNAPSHOT.jar /app/restaurante.jar

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "/app/restaurante.jar"]
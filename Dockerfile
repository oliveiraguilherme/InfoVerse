# Use uma imagem base com suporte ao Java 22 e ao Maven
FROM openjdk:22 AS build

# Copie o arquivo JAR compilado da etapa de compilação para o diretório de trabalho no contêiner
COPY target/collect-0.0.1-SNAPSHOT.jar collect-0.0.1-SNAPSHOT.jar

# Expõe a porta em que sua aplicação será executada
EXPOSE 8088

# Comando para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "collect-0.0.1-SNAPSHOT.jar"]

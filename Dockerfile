# Fase de build: compilação do projeto
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /product-app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY . .
RUN mvn clean package -DskipTests

# Verificação de conteúdo para depuração
RUN ls -l /product-app/target

# Fase de runtime: execução da aplicação
FROM openjdk:17-jdk-alpine

WORKDIR /product-app

COPY --from=build /product-app/target/*.jar product-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

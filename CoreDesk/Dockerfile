# Етап 1: Збирання проєкту за допомогою Maven та JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Копіюємо конфігурацію Maven та вихідний код
COPY pom.xml .
COPY src ./src

# Збираємо проєкт та пропускаємо тести, щоб зекономити час при деплої
RUN mvn clean package -DskipTests

# Етап 2: Запуск готового jar-файлу на полегшеному образі Java
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копіюємо скомпільований jar-файл з першого етапу
COPY --from=build /app/target/*.jar app.jar

# Відкриваємо порт 8080 для зовнішнього світу
EXPOSE 8080

# Команда для запуску застосунку всередині контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
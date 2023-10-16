# Utiliser l'image Java 1.8 comme base
FROM openjdk:8-jre-alpine
LABEL author="Amine MAOURID"

# Définir le répertoire de travail à /app
WORKDIR /app

# Copier le fichier JAR exécutable dans l'image Docker
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

EXPOSE 8761

# Commande pour lancer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "eureka-server.jar"]

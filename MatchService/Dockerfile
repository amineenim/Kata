# Utiliser l'image Java 1.8 comme base
FROM openjdk:8-jre-alpine

LABEL authors="chevenslove.edouard"

# Définir le répertoire de travail à /app
WORKDIR /app

# Copier le fichier JAR exécutable dans l'image Docker
COPY target/match-service-0.0.1-SNAPSHOT.jar match-service.jar

EXPOSE 9093

# Commande pour lancer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "match-service.jar"]
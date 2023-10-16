FROM openjdk:8-jre-alpine

# Définir le répertoire de travail à /app
WORKDIR /app

# Copier le fichier JAR exécutable dans l'image Docker (assurez-vous que le fichier JAR a le bon nom)
COPY target/player-service-0.0.1-SNAPSHOT.jar player-service.jar

EXPOSE 8011

# Commande pour lancer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "player-service.jar"]

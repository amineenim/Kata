FROM openjdk:8-jre-alpine
LABEL author="Amine MAOURID"

# Définir le répertoire de travail à /app
WORKDIR /app

# Copier le fichier JAR exécutable dans l'image Docker (assurez-vous que le fichier JAR a le bon nom)
COPY target/stats-service-0.0.1-SNAPSHOT.jar stats-service.jar

EXPOSE 8014

# Commande pour lancer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "stats-service.jar"]
# Bar'App API

## Configuration requise

- Java 17
- Node
- Apache Maven
- Docker

## Installation

1. Cloner le repository depuis GitHub : git clone https://github.com/JusGaga/BarApp-API.git
2. Accéder au répertoire du projet : cd BarAppAPI
3. Faire un `docker compose up -d` pour installer la base de donnée
4. Construire le projet avec Maven : mvn clean package
5. Lancer l'application : java -jar target/barAppAPI-0.0.1-SNAPSHOT.jar
6. L'API sera accessible à l'adresse : http://localhost:8080

## Script Sql

Pour ajouter des données afin de tester l'application, je vous ai mis à disposition un `sqlScrip.sql` contenant de la data afin de pouvoir tester l'application.

Pour ajouter ses données, je vous recommande d'ouvrir votre base de données et d'y glisser le script et d'exécuter son contenu.

Voilà vous êtes maintenant prêt à utiliser l'application.


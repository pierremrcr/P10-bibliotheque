
### I Téléchargements des outils et logiciels nécessaires

Pour déployer l'application vous aurez besoin des logiciels suivants :
Java et JDK
Apache Tomcat (version 9)
Apache Maven
PostgreSQL 


Pour ce faire, créez vous un dossier nommé «projet10» par exemple sur le bureau afin d’y mettre les logiciels nécessaires au déploiement.


### II Déploiement et démarrage de l’API

Aller à cette adresse : https://github.com/pierremrcr/P10-bibliotheque/tree/master
Faites un git clone du repository dans le dossier de votre choix (Exemple P10).

Ouvrez un terminal puis avec les lignes de commandes, positionnez vous à la racine du projet “back-end-api-biblio”

Copiez-coller le Jar suivant bibliotheque-1.0.0-RELEASE.war (se trouvant à la racine du projet) dans le dossier P10-bibliotheque/back-end-api-biblio/target

Ouvrez un terminal puis positionnez vous dans le répertoire suivant : 

## P10-bibliotheque/back-end-api-biblio/target


Exécutez la commande java -jar bibliotheque-1.0.0-RELEASE.war


Vérifiez dans la console qu’il n’y ait pas d’erreur. La capture d’écran ci-dessous nous montre qu’il n’y a pas eu d’erreur au moment du démarrage de l’application et que l’application a démarré sur le port 8080 du serveur Tomcat en n allant sur l’URL suivante : 

localhost:8080/ws/livres.wsdl

Vous devriez voir le WSDL de l’API s’afficher.


### III Administration de la base de données 

Nous utiliserons ici pour nos tests le système de gestion de base de données H2. H2 est un système de gestion de base de données (SGBD) léger permettant d’être rapidement configuré et déployé afin de lire ou insérer des données dans notre système.

Rendez vous sur l’URL suivante : http://localhost:8080/h2


Entrez comme Password le mot “Password” puis cliquez sur le bouton Connect.

Vous devriez voir apparaître sur le panneau latéral gauche de la fenêtre les 4 tables représentantes le modèle de données (Emprunt, Exemplaire, Livre, Membre).

Pour l’instant, ces tables sont vides. Nous allons donc les alimenter afin qu’elles contiennent des données.

Ouvrez le fichier nommé “jeu_de_donnees.sql” qui se trouve dans le dossier “base de données” à la racine du projet P10-bibliotheque.

Pour commencer, copiez collez le premier bloc de lignes dans l’éditeur de la console H2 puis cliquez sur le bouton Run puis sur le bouton Clear

Réitérez le même processus pour les autres blocs contenus dans le fichier “jeu_de_donnees.sql”.

Notre base de données est désormais alimentée et contient des données.


### IV Déploiement et ouverture de l’application web

Copiez le WAR qui se trouve dans le dans le répertoire suivant : webapplication/web/target 
puis collez le dans le répertoire suivant : apache-tomcat-9.0.11/webapps

Pour démarrer le serveur, ouvrez un nouveau terminal puis positionnez vous dans le sous-dossier bin de Tomcat en éxécutant la commande suivante : 

cd Tomcat/bin

Puis exécutez la commande :

./ startup.sh si vous êtes sur environnement Linux / Unix
start startup.sh si vous êtes sur environnement Windows

Allez sur l'URL suivante pour accéder à l'application :

http://localhost:8085/bibliotheque-webapplication/


### IV Lancement du batch

Pour lancer le batch, placez vous dans le dossier « webapplication » puis dans le dossier « batch » et enfin dans le dossier target.

Ouvrez votre invite de commande et collez le chemin suivant : P10-bibliotheque/webapplication/batch/target/java –jar batch-0.0.1-SNAPSHOT.jar

 





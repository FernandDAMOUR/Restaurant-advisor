# Groupe de jezier_c 956771

Pour ce Projet nous avons crée une Api  à l'aide de laravel donc voilà les commandes à suivre pour pouvoir avoir une application fonctionnelle :
tous d'abord après avoir clone le repository il vous faudra faire :
-   un composer install /composer update pour pouvoir avoir le dossier vendor. 
-   vous devrez cree un fichier .env en vous servant du template .envexample avec le bon nom de votre base de donnée utilisé comme mysql, mariaDB ou autre , le tout que vous auriez bien crée en amont.

-   une fois ceci fait vous allez faire un php artisan serve 
-   puis un php artisan migrate --seed pour pouvoir envoyer vos migrations avec des seeders a l'interieur de votre base de donnée (seeders = donnée fictive pour pouvoir realiser des test.)
Vous pouvez ensuite installer Postman/insomnia pour pouvoir tester les routes de l'api.

- Pour executer l'application il faut impérativement l'executer sur un émulateur et lancé l'API (avec php artisan serve) 

<p style="text-align: right;">Julien Quartier & Nathan Séville</p>
# SYM - Laboratoire 4 - Rapport

> 1. Question
>
>    Une fois la manipulation effectuée, vous constaterez que les animations de la flèche ne sont pas fluides, il va y avoir un tremblement plus ou moins important même si le téléphone ne bouge pas. Veuillez expliquer quelle est la cause la plus probable de ce tremblement et donner une manière (sans forcément l’implémenter) d’y remédier.



> 2. Questions
>    - La caractéristique permettant de lire la température retourne la valeur en degrés Celsius, multipliée par 10, sous la forme d’un entier non-signé de 16 bits. Quel est l’intérêt de procéder de la sorte ? Pourquoi ne pas échanger un nombre à virgule flottante de type float par exemple ?

Réduction de la dimension de la donnée transmise en utilisant par un `int` de 16 bits à la place d'un `float` en occupant 32bit, la durée de la transmission est réduite et donc la consommation de batterie également. Permet également d'être certain de la précision de la valeur transmise. 

----- Plus ??? ------

> 2. Questions
>    - Le niveau de charge de la pile est à présent indiqué uniquement sur l’écran du périphérique, mais nous souhaiterions que celui-ci puisse informer le smartphone sur son niveau de charge restante. Veuillez spécifier la(les) caractéristique(s) qui composerai(en)t un tel service, mis à disposition par le périphérique et permettant de communiquer le niveau de batterie restant via Bluetooth Low Energy. Pour chaque caractéristique, vous indiquerez les opérations supportées (lecture, écriture, notification, indication, etc.) ainsi que les données échangées et leur format.

Seul un caractéristique est nécessaire pour implémenter cette fonctionnalité. Etant donné que le niveau de batterie est généralement représenté avec une valeur entre 0 et 100 (représentant le niveau de charge en pourcentage) seul un entier sur 8 bit est nécessaire(uint8). Il est intéressant de n'être informé que lors du changement de niveau de la batterie donc un caractéristique basé sur le modèle notification est bien adapté à cette utilisation.

En résumé:

* uint8
* notification



----- Première lecture ? caractéristique supplémentaire en lecture ? ------
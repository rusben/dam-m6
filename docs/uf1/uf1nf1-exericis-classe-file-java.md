
# Exercicis
## Exercici 1
Implementar la classe `MostraFitxers.java` que implementa una versió simple de `ls`.

Fes un programa que rebi un nom de directori com a paràmetre i mostri el seu contingut, indicant en cada cas si es tracta d'un fitxer o directori i els permisos que tenim sobre ell.

La sortida tindrà un aspecte similar a aquest:

```java
-rw- fitxer
drwx directori
...
```
## Exercici 2
Fes una versió simple de `ls -R`. Fes un programa que rebi un nom de directori com a paràmetre i mostri el seu contingut, indicant en cada cas si es tracta d'un fitxer o directori i els permisos que tenim sobre ell.
El programa actuarà recursivament, mostrant el contingut de tots els subdirectoris amb què es vagi trobant.

Per fer-ho, utilitza una pila on es guardin els noms de directoris pendents de mostrar.

## Exercici 3
Crear un arxiu al gestor de fitxers mitjançant una utilitat qualsevol en un directori determinat amb les propietats que vulgueu mitjançant la shell del SO. Implementar la classe `TestFitxer.java` que permeti:
* Crear un objecte `File` amb la ruta a aquest arxiu i imprimir el nom complet de l'arxiu (i indicar que és el nom complet).
* Comprobar que l'arxiu existeix i mostrar la ruta al directori on es troba.
* Testejar els mètodes que ofereix la classe File per a mostrar diferent informació del fitxer
Si és que no existeix, ensenyar per pantalla aquesta informació.

## Exercici 4
Implementar la classe `CreaDir.java` que en la seva execució permeti:
* Crear la ruta a un directori.
* Crear el directori associat a la ruta anterior.
* Mirar si el directori existeix, i en cas de que s'hagi creat mostrar per pantalla el nom del directori i en altre cas imprimir "que no ha estat possible crear el directori" .

## Exercici 5
A partir de la implementació de la classe `MostraFitxers.java`, crea una nova classe anomenada `MostraInfoDir.java` que mostri una informació semblant a la comanda de la `shell` de Linux `ls`.

# Gestió del sistema de fitxers
* [Gestió del sistema de fitxers](#uf1nf1-gestio-sistema-fitxers)
* [Exemples: Llegir informació introduïda per l'usuari](uf1nf1-exemples-lectura-informacio.md)
* [La classe File de Java](uf1nf1-classe-file-java.md)
* [Exemples: Utilització de la classe File de Java](uf1nf1-exemples-classe-file-java.md)
* [Exercicis](uf1nf1-exericis-classe-file-java.md)

# Gestió del sistema de fitxers <a name="uf1nf1-gestio-sistema-fitxers"></a>
Com Java és un llenguatge orientat a objectes, la majoria d'aplicacions tenen un conjunt de classes de dades.

En la memòria resideixen les instàncies d'aquestes classes de dades i els components de vista i controlador (interfície d'usuari).

Per emmagatzemar les dades de forma permanent una aplicació ha de prendre el model de dades de la memòria i escriure-ho en disc.

Un arxiu, també anomenat `fitxer`, és un conjunt de dades emmagatzemades com una `unitat` en un suport físic, per exemple un disc magnètic o CD.

Les dades emmagatzemades en un arxiu són de manera permanent de manera que poden ser manipulats en qualsevol moment.

Cada fitxer està referenciat mitjançant un identificador únic (`ruta + nom`).

En els sistemes informàtics actuals, en els quals un sol ordinador pot tenir ben bé més d’un milió de fitxers, resulta imprescindible un sistema que permeti una gestió eficaç de localització, de manera que els usuaris puguem moure’ns còmodament entre tants arxius. La majoria de sistemes de fitxers han incorporat contenidors jerarquitzats que actuen a mode de directoris facilitant la classificació, la identificació i localització dels arxius.
Els directoris s’han acabat popularitzant sota la versió gràfica de carpetes.
Hem de tenir en compte, a més, que la necessitat desmesurada d’espai d’emmagatzematge ha dut els SO a treballar amb una gran quantitat de dispositius i a permetre l’accés remot a sistemes de fitxers aliens, distribuïts per la xarxa.

## Exemples - Windows

```console
Nom complet: C:\Farmacia\Datos\Balance.dat

Nom curt: Balance.dat

Extensió: .dat

Ruta: C:\Farmacia\Datos\
```

## Exemples - Linux

```console
Nom complet: /home/ccp/farmacia/datos/Balance.dat

Nom curt: Balance.dat

Extensió: .dat

Ruta: /home/ccp/farmacia/datos/
```

En Java, en el paquet `java.io`, hi ha diferents classes que faciliten treballar amb fitxers des de diferents perspectives:

* Fitxers d'accés seqüencial o accés aleatori,
* Fitxers de caràcters o fitxers de bytes (binaris).

Els dos primers són una classificació segons el tipus de contingut que guarden. Els dos últims són classificats segons la manera d'accés.

## Criteris segons el tipus de contingut

* ***Fitxers de caràcters (o de text)***: són aquells creats exclusivament amb caràcters, de manera que poden ser creats i visualitzats utilitzant qualsevol editor de text que ofereixi el sistema operatiu (per exemple: Notepad, Edit, etc).

* ***Fitxers binaris (o de bytes)***: són aquells que no contenen caràcters recognoscibles sinó que els bytes que contenen representen altra informació: imatges, música, vídeo, etc Aquests fitxers només poden ser oberts per aplicacions concretes que entenguin com estan disposats els bytes dins del fitxer, i així poder reconèixer la informació que conté.

## Criteris segons el mode d'accés

* ***Fitxers seqüencials***: en aquest  tipus de fitxers la informació és emmagatzemada com una seqüència de bytes (o caràcters), de manera que per accedir al byte (o caràcter) i-èsim, cal passar abans per tots els anteriors (i-1).

* ***Fitxers aleatoris***: a diferència dels anteriors l'accés pot ser directament a una posició concreta fitxer, sense necessitat de recórrer les dades anteriors. Un exemple d'accés aleatori en programació és el ús d'arrays.

En les següents seccions es mostren alternatives per a l'accés a fitxers segons siguin d'un tipus o un altre. No obstant això, amb independència de la manera, Java defineix una classe dins del paquet java.io que representa un arxiu o un directori dins d'un sistema de fitxers. Aquesta classe és `File`.

Un objecte de la classe `File` representa el nom d'un fitxer o d'un directori que existeix en el sistema de fitxers (els mètodes de `File` permeten obtenir tota la informació sobre les característiques del fitxer o directori).

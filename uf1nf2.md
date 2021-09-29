# Magatzems i fluxos de dades
En la primera part de la UF vam veure en Java (en el paquet java.io) que hi ha diferents classes que faciliten treballar amb fitxers des de diferents perspectives:

* Fitxers d'accés seqüencial o accés aleatori,
* Fitxers de caràcters o fitxers de bytes (binaris).

Els dos primers són una classificació segons el tipus de contingut que guarden. Els dos últims són classificats segons la manera d'accés.

Abans d'aprofundir en aquest tema cal clarificar el concepte de fitxers com a magatzems de contingut, i el concepte de contingut com a flux de dades.

## Els fitxers

Des del punt de vista de l’usuari, els fitxers són magatzems permanents d'informació guardada en forma de dades estructurades i ben organitzades de manera que la seva identificació i conseqüent interpretació siguin fàcils. Aquesta és una perspectiva centrada en la representació mental de la informació i s’anomena estructura lògica.

L'estructura lògica o representació conceptual de les dades va canviant a mida que avança pels diferents nivells d'abstracció fins a arribar al dispositiu d'emmagatzematge. La forma final de les dades es coneix també com estructura física. Ara bé, en el camí de la persistència, la representació mental original passarà per diferents nivells d'abstracció fins arribar a l’emmagatzematge físic(figura anterior).

Cada nivell imposarà les seves limitacions a la representació del nivell anterior introduint canvis que alteraran l'estructura original.

El primer nivell d'abstracció el trobem en el llenguatge de programació utilitzat, que depenent de les estructures d'informació suportades, els tipus de dades permesos, la representació en memòria d'aquest, etc., alterarà en alguna mesura la representació de partida.

El Sistema Operatiu imposarà també les seves restriccions i ho farà també el driver o adaptador al sistema d'emmagatzematge, o els mecanismes físics i els suport escollits en últim terme.

L’estructura final, com les dades acaben emmagatzemant-se, s’anomena també estructura física.

## Seriació

Les referències a memòria són dades transitòries que varien en cada execució. Si guardéssim de forma persistent les referències a memòria, en recuperar-les obtindríem dades totalment incoherents perquè apuntarien a dades inexistents o ocupades per altra informació.

Si volem un emmagatzematge i una recuperació de les dades eficaç, caldrà assegurar que totes les dades referenciades s’emmagatzemen també al fitxer i es relacionen entre elles de forma que sigui factible
localitzar-les i recuperar-les conjuntament.

La forma més senzilla de fer-ho consisteix a compactar les successives dades referenciades agrupant-les, una darrera l’altra, en una única seqüència de dades primitives lliure de referències.

Aquest procés s’anomena seriació i cal utilitzar-lo per transformar estructures complexes de la memòria principal en sèries de dades compactes i fàcilment emmagatzemables.

## Flux de dades

En Java, l'accés a fitxers és tractat com un flux d'informació entre el programa i el fitxer. Un flux no és més que un objecte que fa d'intermediari entre el programa i l'origen o la destinació de la informació.

Aquesta abstracció proporcionada pels fluxos fa que els programadors, quan volen accedir a informació, només s'hagin de preocupar per treballar amb els objectes que proporcionen el flux, sense importar l'origen o la destinació concreta d'on vinguin o vagin les dades.

Qualsevol programa que hagi de obtenir informació de qualsevol font necessita obrir un stream, igualment si necessita enviar informació obrirà un stream i s'escriurà la informació. La vinculació d'aquest stream amb el dispositiu físic la fa el sistema d'entrada / sortida de Java.

## Conceptes bàsics sobre fitxers seqüencials

Començarem veient les operacions sobre fitxers seqüencials i després treballarem amb els aleatoris.

En els primers veurem les classes per la gestió dels fluxos de dades, primer en el cas dels fluxos binaris i després en el cas dels fluxos de caràcters.

* Accés seqüencial: les dades o registres es llegeixen i s'escriuen en ordre. Si es vol accedir a una dada cal llegir abans tots els anteriors. L'escriptura de dades es fa a partir de l'última dada escrita, no és
possible fer insercions entre les dades que ja hi existeixen.

Operacions bàsiques sobre fitxers:

* Creació del fitxer.
* Obertura del fitxer.
* Tancament del fitxer.
* Lectura de les dades del fitxer.
* Escriptura de les dades en el fitxer.

En els fitxers seqüencials els registres s'insereixen en ordre cronològic. Si cal afegir nous registres aquests s'afegeixen a partir final del fitxer:

* Consultes: Per consultar un determinat registre és necessari començar la lectura des del primer registre, i continuar llegint seqüencialment fins a localitzar el registre buscat.

* Altes: les altes es realitzen al final de l'últim registre inserit, només es permet afegir dades al final del fitxer.

* Baixes: per donar de baixa un registre cal fer ús d'un fitxer auxiliar, llegint tots els registres i copiar tots a excepció del que vulguem donar de baixa. Finalment cal canviar el nom del fitxer auxiliar donant-li el nom del fitxer original.

* Modificacions: El procés de modificacions és similar al de baixes

### Creating a File

If you want to create a new file, you must use a class that is equipped to write values to a file. To do this, you can use the PrintWriter class. The PrintWriter class is defined in the java.io package. Therefore, if you want to use it, you can import it in your document. This would be done as follows:

```java
import java.io.PrintWriter;

public class Exercise {
    public static void main(String[] args)  throws Exception {

    }
}
```

The PrintWriter class is based on (implements) the Writer class. The class is equipped with the necessary means of writing values to a file.

Before using the class, declare a variable for it. This class is equipped with many constructors. One of the constructors takes as argument an OutputStream object. We saw that the File class is based on OutputStream. This means that you can pass a File object to a PrintWriter constructor. This would be done as follows:

```java
import java.io.File;
import java.io.PrintWriter;

public class Exercise {
  public static void main(String[] args)  throws Exception {
  	// Indicate that you are planning to use a file
  	File fleExample = new File("Example.xpl");
    // Create that file and prepare to write some values to it
    PrintWriter pwInput = new PrintWriter(fleExample);
  }
}
```

## Writing to a File

After creating a PrintWriter object, you can write values to the file. To support this, the PrintWriter class is equipped with the print() and println() methods that is overloaded with various versions for each type of values (boolean, char, char[], int, float, double, String, or Object). Therefore, to write a value to a file, call the appropriate version of the PrintWriter.print() method and pass the desired value. Here are examples:

```java
import java.io.File;
import java.io.PrintWriter;

public class Exercise {
  public static void main(String[] args)  throws Exception {
    // Indicate that you are planning to use a file
    File fleExample = new File("Example.xpl");
    // Create that file and prepare to write some values to it
    PrintWriter pwInput = new PrintWriter(fleExample);

    // Write a string to the file
    pwInput.println("Francine");
    // Write a string to the file
    pwInput.println("Mukoko");
    // Write a double-precision number to the file
    pwInput.println(22.85);
    // Write a Boolean value to the file
    pwInput.print(true);
  }
}
```

After using a PrintWriter object, you should free the resources it was using. To assist you with this, the PrintWriter class is equipped with the Close() method. Here is an example of calling:

```java
import java.io.File;
import java.io.PrintWriter;

public class Exercise {
  public static void main(String[] args)  throws Exception {
    // Indicate that you are planning to use a file
    File fleExample = new File("Example.xpl");
    // Create that file and prepare to write some values to it
    PrintWriter pwInput = new PrintWriter(fleExample);

    // Write a string to the file
    pwInput.println("Francine");
    // Write a string to the file
    pwInput.println("Mukoko");
    // Write a double-precision number to the file
    pwInput.println(22.85);
    // Write a Boolean value to the file
    pwInput.print(true);
    // After using the PrintWriter object, de-allocated its memory
    pwInput.close();
    // For convenience, let the user know that the file has been created
    System.out.println("The file has been created.");   
  }
}
```

### Opening a File

Besides creating a file, the second most common operation performed on a file consists of opening one. You can open a file using the File class. As done previously, first declare a File variable and pass the name of the file to its constructor. Here is an example:

```java
import java.io.File;

public class Exercise {
  public static void main(String[] args)  throws Exception {
    // Incidate that you are planning to opena file
	  File fleExample = new File("Example.xpl");
  }
}
```

### Exercicis

#### Exercici 1
Implementa la classe `NumsToFile1.java` que escriu els números de `1100 a 1200` en un arxiu que amb nom `Num11001200.txt` a l'escriptori.

#### Exercici 2
Implementa la classe `CharsToFile1.java` que escrigui les lletres de `a` a `z` (una en cada línia i en minúscules) en un arxiu que hem anomenat `alphabeta.txt` a l'escriptori.

#### Exercici 3
Implementa la classe `CharsFileToScreen1.java` Llegir l'arxiu de l'exercici 2 i escriure en pantalla els caràcters un al costat de l'altre i en majúscules amb un espai entre cada caràcter.

#### Exercici 4
Implementa la classe `TreballadorsToFile1.java` . Cal que llegeixi valors des de consola `(Nom, Cognoms, Salari,  Casat)` de tants treballadors fins que en respondre a la pregunta `Vols Continuar?` la seva resposta sigui diferent a `Y`. Aquestes dades s'hauran d'emmagatzemar en un fitxer anomenat `treballadors.txt`. En aquest arxiu en cada fila s'emmagatzemarà un registre amb els valors separats per comes, amb tants registres com els que s'han introduït per consola.

#### Exercici 5
Implementa la classe `TreballadorsToScreen1.java`. Cal que llegeixi valors del fitxer `treballadors.txt` i mostri el resultat amb el següent format exemple:

```console
TREBALLADOR 1

Gerard

Paulino

1500

false

TREBALLADOR 2

.....

```

# Gestió del sistema de fitxers
Com Java és un llenguatge orientat a objectes, la majoria d'aplicacions tenen un conjunt de classes de dades.

En la memòria resideixen les instàncies d'aquestes classes de dades i els components de vista i controlador (interfície d'usuari).

Per emmagatzemar les dades de forma permanent una aplicació ha de prendre el model de dades de la memòria i escriure-ho en disc.

Un arxiu, també anomenat "fitxer", és un conjunt de dades emmagatzemades com una "unitat" en un suport físic, per exemple un disc magnètic o CD.

Les dades emmagatzemades en un arxiu són de manera permanent de manera que poden ser manipulats en qualsevol moment.

Cada fitxer està referenciat mitjançant un identificador únic (ruta + nom).

En els sistemes informàtics actuals, en els quals un sol ordinador pot tenir ben bé més d’un milió de fitxers, resulta imprescindible un sistema que permeti una gestió eficaç de localització, de manera que els usuaris puguem moure’ns còmodament entre tants arxius. La majoria de sistemes de fitxers han incorporat contenidors jerarquitzats que actuen a mode de directoris facilitant la classificació, la identificació i localització dels arxius.
Els directoris s’han acabat popularitzant sota la versió gràfica de carpetes.
Hem de tenir en compte, a més, que la necessitat desmesurada d’espai d’emmagatzematge ha dut els SO a treballar amb una gran quantitat de dispositius i a permetre l’accés remot a sistemes de fitxers aliens, distribuïts per la xarxa.

### Exemples - Windows

```console
Nom complet: C:\Farmacia\Datos\Balance.dat

Nom curt: Balance.dat

Extensió: .dat

Ruta: C:\Farmacia\Datos\
```

### Exemples - Linux

```console
Nom complet: /home/ccp/farmacia/datos/Balance.dat

Nom curt: Balance.dat

Extensió: .dat

Ruta: /home/ccp/farmacia/datos/
```

En Java, en el paquet java.io, hi ha diferents classes que faciliten treballar amb fitxers des de diferents perspectives:

* Fitxers d'accés seqüencial o accés aleatori,
* Fitxers de caràcters o fitxers de bytes (binaris).

Els dos primers són una classificació segons el tipus de contingut que guarden. Els dos últims són classificats segons la manera d'accés.

Criteris segons el tipus de contingut:

* Fitxers de caràcters (o de text): són aquells creats exclusivament amb caràcters, de manera que poden ser creats i visualitzats utilitzant qualsevol editor de text que ofereixi el sistema operatiu (per exemple: Notepad, Edit, etc).

* Fitxers binaris (o de bytes): són aquells que no contenen caràcters recognoscibles sinó que els bytes que contenen representen altra informació: imatges, música, vídeo, etc Aquests fitxers només poden ser oberts per aplicacions concretes que entenguin com estan disposats els bytes dins del fitxer, i així poder reconèixer la informació que conté.

Criteris segons el mode d'accés:

* Fitxers seqüencials: en aquest  tipus de fitxers la informació és emmagatzemada com una seqüència de bytes (o caràcters), de manera que per accedir al byte (o caràcter) i-èsim, cal passar abans per tots els anteriors (i-1).

* Fitxers aleatoris: a diferència dels anteriors l'accés pot ser directament a una posició concreta fitxer, sense necessitat de recórrer les dades anteriors. Un exemple d'accés aleatori en programació és el ús d'arrays.

En les següents seccions es mostren alternatives per a l'accés a fitxers segons siguin d'un tipus o un altre. No obstant això, amb independència de la manera, Java defineix una classe dins del paquet java.io que representa un arxiu o un directori dins d'un sistema de fitxers. Aquesta classe és `File`.

Un objecte de la classe `File` representa el nom d'un fitxer o d'un directori que existeix en el sistema de fitxers (els mètodes de File permeten obtenir tota la informació sobre les característiques del fitxer o directori).

## Configuració d'Eclipse


## Configuració d'IntelliJ


## Programa en Java per a llegir informació introduïda per l'usuari

### Exemple 1
Hi ha diverses maneres d'obtenir informació de l'usuari. Aquí tenim un programa que mitjançant la classe `Scanner` de Java assolirà aquesta tasca. Aquesta classe `Scanner` pertany a `java.util`, per tant la primera línia del programa és `import java.util.Scanner;` que permet a l'usuari llegir valors de diversos tipus a Java.

```java
import java.util.Scanner;

public class RepeatStrings {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int i = reader.nextInt();
		for (int j = 0; j < i; j++)
			System.out.println("I love Java.");
		reader.close();
	}
}
```

Les declaracions dels `import` han d'estar a les primeres línies del programa. Per accedir als mètodes de la classe `Scanner`, creeu un nou objecte d'escàner amb el nom `in`. Ara podem fem servir el seu mètode `next`. El mètode `next` obté la cadena de text que un usuari introdueix al teclat. Depenent de la vostra necessitat, utilitzeu aquest mètode: `reader.nextInt();` per llegir només els números, no el retorn de línia ni res que hi hagi després del número.


### Exemple 2
Fem servir `in.nextLine()` per llegir el text sencer que ha escrit l'usuari.
```java
import java.util.Scanner;

public class GetInputFromUser {
	public static void main(String[] args) {
    int a;
    float b;
    String s;
    Scanner in = new Scanner(System.in);

    System.out.println("Enter a string");
    s = in.nextLine();
    System.out.println("You entered string "+s);
    System.out.println("Enter a integer");
    a = in.nextInt();
    System.out.println("You entered integer "+a);
    System.out.println("Enter a float");
    b = in.nextFloat();
    System.out.println("You entered float"+b);
    in.close();
  }
}

```


### Exemple 3
Aquí el programa demana a l'usuari que introdueixi un número enter amb `in.nextInt()`. Després, el programa imprimeix els dígits del número i la suma dels dígits.

```java
import java.util.Scanner;

public class PrintNumber {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
    int num = 0;
    int sum = 0;

    System.out.println("Please enter a number to show its digits");
    num = reader.nextInt();

    System.out.println("Below you can see the digits and the sum of the digits");

    while (num > 0) {
      System.out.println("==>" + num % 10);
      sum += num % 10;
      num = num / 10;
    }

    System.out.println("The sum is: "+ sum);
	}
}
```

### Exemple 4
Implementació de la classe `SuperCalculator`.

```java
import java.util.Scanner;

public class SuperCalculator {
	public static void main(String[] args) {
    int input, a, b;
    input = a = b = 0;
    Scanner in = new Scanner(System.in);

    System.out.println("The super calculator");
    System.out.println("enter the corrsponding number:");
    System.out.println("1. Add | 2. Substract | 3. Divide | 4. Multiply");

    input = in.nextInt();

    a = in.nextInt();
    b = in.nextInt();

    switch (input) {
      case 1: System.out.println(a + " + " + b + " = " + add(a, b));
        break;
      case 2: System.out.println(a + " - " + b + " = " + substract(a, b));
        break;
      case 3: System.out.println(a + " / " + b + " = " + divide(a, b));
        break;
      case 4: System.out.println(a + " * " + b + " = " + multiply(a, b));
        break;
      default: System.out.println("Your input is invalid.");
    }
  }

  static int add(int lhs, int rhs) { return lhs + rhs; }
  static int substract(int lhs, int rhs) { return lhs - rhs; }
  static int divide(int lhs, int rhs) { return lhs / rhs; }
  static int multiply(int lhs, int rhs) { return lhs * rhs; }
}
```

## La classe File de Java

La classe `File` de Java representa els fitxers i els noms de ruta del directori de manera abstracta. Aquesta classe s'utilitza per a la creació de fitxers i directoris, cerca de fitxers, eliminació de fitxers, etc.

L’objecte File representa el fitxer o directori real del disc.

A continuació es mostra la llista de constructors per crear un objecte `File`.

### Constructors de la classe File

#### File(File parent, String child)
This constructor creates a new File instance from a parent abstract pathname and a child pathname string.

#### File(String pathname)
This constructor creates a new File instance by converting the given pathname string into an abstract pathname.

#### File(String parent, String child)
This constructor creates a new File instance from a parent pathname string and a child pathname string.

#### File(URI uri)
This constructor creates a new File instance by converting the given file URI into an abstract pathname.

### Mètodes per manipular objectes de la classe File

####	public String getName()
Returns the name of the file or directory denoted by this abstract pathname.

#### public String getParent()
Returns the pathname string of this abstract pathname's parent, or null if this pathname does not name a parent directory.

#### public File getParentFile()
Returns the abstract pathname of this abstract pathname's parent, or null if this pathname does not name a parent directory.

#### public String getPath()
Converts this abstract pathname into a pathname string.

#### public boolean isAbsolute()
Tests whether this abstract pathname is absolute. Returns true if this abstract pathname is absolute, false otherwise.

#### public String getAbsolutePath()
Returns the absolute pathname string of this abstract pathname.

#### public boolean canRead()
Tests whether the application can read the file denoted by this abstract pathname. Returns true if and only if the file specified by this abstract pathname exists and can be read by the application; false otherwise.

#### public boolean canWrite()
Tests whether the application can modify to the file denoted by this abstract pathname. Returns true if and only if the file system actually contains a file denoted by this abstract pathname and the application is allowed to write to the file; false otherwise.

#### public boolean exists()
Tests whether the file or directory denoted by this abstract pathname exists. Returns true if and only if the file or directory denoted by this abstract pathname exists; false otherwise.

#### public boolean isDirectory()
Tests whether the file denoted by this abstract pathname is a directory. Returns true if and only if the file denoted by this abstract pathname exists and is a directory; false otherwise.

#### public boolean isFile()
Tests whether the file denoted by this abstract pathname is a normal file. A file is normal if it is not a directory and, in addition, satisfies other system-dependent criteria. Any non-directory file created by a Java application is guaranteed to be a normal file. Returns true if and only if the file denoted by this abstract pathname exists and is a normal file; false otherwise.

#### public long lastModified()
Returns the time that the file denoted by this abstract pathname was last modified. Returns a long value representing the time the file was last modified, measured in milliseconds since the epoch (00:00:00 GMT, January 1, 1970), or 0L if the file does not exist or if an I/O error occurs.

#### public long length()
Returns the length of the file denoted by this abstract pathname. The return value is unspecified if this pathname denotes a directory.

#### public boolean createNewFile() throws IOException
Atomically creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist. Returns true if the named file does not exist and was successfully created; false if the named file already exists.

#### public boolean delete()
Deletes the file or directory denoted by this abstract pathname. If this pathname denotes a directory, then the directory must be empty in order to be deleted. Returns true if and only if the file or directory is successfully deleted; false otherwise.

#### public void deleteOnExit()
Requests that the file or directory denoted by this abstract pathname be deleted when the virtual machine terminates.

#### public String[] list()
Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.

#### public String[] list(FilenameFilter filter)
Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.

#### public File[] listFiles()
Returns an array of abstract pathnames denoting the files in the directory denoted by this abstract pathname.

#### public File[] listFiles(FileFilter filter)
Returns an array of abstract pathnames denoting the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.

#### public boolean mkdir()
Creates the directory named by this abstract pathname. Returns true if and only if the directory was created; false otherwise.

#### public boolean mkdirs()
Creates the directory named by this abstract pathname, including any necessary but nonexistent parent directories. Returns true if and only if the directory was created, along with all necessary parent directories; false otherwise.

#### public boolean renameTo(File dest)
Renames the file denoted by this abstract pathname. Returns true if and only if the renaming succeeded; false otherwise.

#### public boolean setLastModified(long time)
Sets the last-modified time of the file or directory named by this abstract pathname. Returns true if and only if the operation succeeded; false otherwise.

#### public boolean setReadOnly()
Marks the file or directory named by this abstract pathname so that only read operations are allowed. Returns true if and only if the operation succeeded; false otherwise.

#### public static File createTempFile(String prefix, String suffix, File directory) throws IOException
Creates a new empty file in the specified directory, using the given prefix and suffix strings to generate its name. Returns an abstract pathname denoting a newly-created empty file.

#### public static File createTempFile(String prefix, String suffix) throws IOException
Creates an empty file in the default temporary-file directory, using the given prefix and suffix to generate its name. Invoking this method is equivalent to invoking createTempFile(prefix, suffix, null). Returns abstract pathname denoting a newly-created empty file.

#### public int compareTo(File pathname)
Compares two abstract pathnames lexicographically. Returns zero if the argument is equal to this abstract pathname, a value less than zero if this abstract pathname is lexicographically less than the argument, or a value greater than zero if this abstract pathname is lexicographically greater than the argument.

#### public int compareTo(Object o)
Compares this abstract pathname to another object. Returns zero if the argument is equal to this abstract pathname, a value less than zero if this abstract pathname is lexicographically less than the argument, or a value greater than zero if this abstract pathname is lexicographically greater than the argument.

#### public boolean equals(Object obj)
Tests this abstract pathname for equality with the given object. Returns true if and only if the argument is not null and is an abstract pathname that denotes the same file or directory as this abstract pathname.

#### public String toString()
Returns the pathname string of this abstract pathname. This is just the string returned by the getPath() method.

### Exemples
#### Exemple 1
```java
import java.io.File;

public class ShowFiles {
	public static void main(String[] args) {

    System.out.println("Show the files in the current directory:");
    File f = new File(".");

    String[] files = f.list();

    for (int i = 0; i < files.length; i++) {
      System.out.println(files[i]);
    }

    System.out.println(" ######################### ");
    System.out.println("Returns an array of File Objects indicating the available file system roots.");

    File[] roots = File.listRoots();
    for (File root : roots) {
      System.out.println(root)
    }
  }
}
```
#### Exemple 2
```java
import java.io.File;

public class CreateFile {
    public static void main(String[] args) {
      System.out.println("Creació de llibre.xml");

      File book = new File("llibre.xml");

      System.out.println("Nom: "+book.getName());
      System.out.println("Directori pare: "+book.getParent());
      System.out.println("Ruta absoluta: "+book.getAbsolutePath());

      // Existeix el fitxer anomenat llibre.xml?

      System.out.println("Existeix el fixter anomenat llibre.xml?");
      System.out.println(book.exists());
    }
}
```

#### Exemple 3
```java
import java.io.File;

public class CreateMoreFiles {
  public static void main(String[] args) {
    File f = null;
    String[] names = {"first.txt", "second.txt"};

    try {
      // foreach name in names array
      for (String name: names) {
        // Create a new file
        f = new File(name);
        // return true if the file is executable
        boolean x = f.canExecute();
        // find absolute path
        String p = f.getAbsolutePath();
        // Prints absolute path
        System.out.println(p);
        // Executable?
        System.out.println( "File is executable? "+ x);
      }
    } catch (Exception e) {
      // If any I/O error occurs
      e.printStackTrace();
    }
  }
}
```

Considereu que teniu un fitxer executable `test1.txt` i un altre fitxer `test2.txt` que no és executable al directori actual. Compilem i executem el programa anterior. Això produirà el resultat següent:

```console
test1.txt és executable: cert
test2.txt és executable: fals
```

### Exercicis
#### Exercici 1
Implementar la classe `MostraFitxers.java` que implementa una versió simple de `ls`.

Fes un programa que rebi un nom de directori com a paràmetre i mostri el seu contingut, indicant en cada cas si es tracta d'un fitxer o directori i els permisos que tenim sobre ell.

La sortida tindrà un aspecte similar a aquest:

```java
-rw- fitxer
drwx directori
...
```

#### Exercici 2
Fes una versió simple de `ls -R`. Fes un programa que rebi un nom de directori com a paràmetre i mostri el seu contingut, indicant en cada cas si es tracta d'un fitxer o directori i els permisos que tenim sobre ell.
El programa actuarà recursivament, mostrant el contingut de tots els subdirectoris amb què es vagi trobant.

Per fer-ho, utilitza una pila on es guardin els noms de directoris pendents de mostrar.

#### Exercici 3
Crear un arxiu al gestor de fitxers mitjançant una utilitat qualsevol en un directori determinat amb les propietats que vulgueu mitjançant la shell del SO. Implementar la classe `TestFitxer.java` que permeti:
* Crear un objecte `File` amb la ruta a aquest arxiu i imprimir el nom complet de l'arxiu (i indicar que és el nom complet).
* Comprobar que l'arxiu existeix i mostrar la ruta al directori on es troba.
* Testejar els mètodes que ofereix la classe File per a mostrar diferent informació del fitxer
Si és que no existeix, ensenyar per pantalla aquesta informació.

#### Exercici 4
Implementar la classe `CreaDir.java` que en la seva execució permeti:
* Crear la ruta a un directori.
* Crear el directori associat a la ruta anterior.
* Mirar si el directori existeix, i en cas de que s'hagi creat mostrar per pantalla el nom del directori i en altre cas imprimir "que no ha estat possible crear el directori" .

#### Exercici 5
A partir de la implementació de la classe `MostraFitxers.java`, crea una nova classe anomenada `MostraInfoDir.java` que mostri una informació semblant a la comanda de la `shell` de Linux `ls`.

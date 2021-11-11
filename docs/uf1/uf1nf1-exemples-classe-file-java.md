# Exemples d'ús de la classe File de Java
## Exemple 1
Mostra els fitxers que es troben en el directori actual.
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
## Exemple 2
Crea un fitxer `llibre.xml`, mostra el seu directori pare i la seva ruta absoluta, després pregunta si el fitxer existeix.
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

## Exemple 3
Crea diferents fitxers, el nom dels quals està indicat a l'array d'String `names`. Per a cada fitxer que crea mostra si es executable i la seva ruta absoluta.
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

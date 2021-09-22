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

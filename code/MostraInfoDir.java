import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MostraInfoDir {

  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    System.out.println("Introdueix la ruta del directori: ");
    String path = reader.nextLine();

    File f = new File(path);

    File[] files = f.listFiles();
    System.out.println("Total " + files.length);

    for (int i = 0; i < files.length; i++) {
      System.out.print(files[i].isDirectory() ? "D" : "-");
      System.out.println(files[i].isHidden() ? "H" : "-");
      System.out.print(files[i].canRead() ? "R" : "-");
      System.out.print(files[i].canWrite() ? "W" : "-");
      System.out.print(files[i].canExecute() ? "X" : "-");

      System.out.print("  " + files[i].length() + "\t");
      System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(files[i].lastModified()))+"\t");
      System.out.println(files[i].getName() + " ");
    }
  }
}

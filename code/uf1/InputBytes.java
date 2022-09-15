import java.io.FileInputStream;
import java.io.IOException;

public class InputBytes {
  public static void main(String[] args) {
    try {

      FileInputStream fis = new FileInputStream("path/to/file.class");
      boolean eof = false;

      int count = 0;

      while (!eof) {
        int data = fis.read();
        System.out.println(data + " ");

        if (data == -1) eof = true;
        else count++;
      }
      fis.close();
      System.out.println("\nTotal number of bytes readed: "+count);

    } catch(IOException e) {
      System.out.println("ERROR");
      e.printStackTrace();
    }
  }
}

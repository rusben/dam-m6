import java.io.IOException;

public class OutputBytes {

  public static void main(String[] args) {
    int data[] = {65, 66, 67, 68, 69};

    try {
      FileOutputStream fos = new FileOutputStream("path/to/file");
      for (int i = 0; i < data.length; i++) {
        fos.write(data[i]);
      }
      fos.close();
    } catch(IOException e) {
      System.out.println("ERROR");
      e.printStackTrace();
    }
  }
}
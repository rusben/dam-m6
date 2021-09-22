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

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

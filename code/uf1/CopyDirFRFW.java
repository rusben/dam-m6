package net.xeill.elpuig;

import java.io.*;

public class CopyDirFRFW {

  public static void main(String[] args) {
    // source -> args[0]
    // detination -> args[1]
    try {
      File source = new File(args[0]);
      File destination = new File(args[1]);
      if (source.isDirectory()) {
        copyFileOrDirectory(source, destination);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void copyFileOrDirectory(File source, File destination) throws IOException {

    if (source.isDirectory()) {
      if (!destination.exists()) {
        destination.mkdir();
        System.out.println("Directori creat.");
      }

      String[] files = source.list();
      for (String file : files) {
        File sourceFile = new File(source, file);
        File destinationFile = new File(destination, file);
        copyFileOrDirectory(sourceFile, destinationFile);
      }

    } else {
      BufferedReader inputStream = new BufferedReader(new FileReader(source));
      BufferedWriter outputStream = new BufferedWriter(new FileWriter(destination, false));

      String line = "";
      while ((line = inputStream.readLine()) != null) {
        outputStream.write(line + "\n");
      }
      inputStream.close();
      outputStream.close();
    }
  }
}



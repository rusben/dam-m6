package net.xeill.elpuig;

import java.io.*;

public class CopyDirFISFOS {

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
      FileInputStream inputStream = new FileInputStream(source);
      FileOutputStream outputStream = new FileOutputStream(destination, false);

      int data;
      while ((data = inputStream.read()) != -1) {
        outputStream.write(data);
      }

      inputStream.close();
      outputStream.close();
    }
  }
}

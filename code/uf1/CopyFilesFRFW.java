package net.xeill.elpuig;

import java.io.*;

public class CopyFilesFRFW {

  // Paramétro 1: source file
  // Parámetro 2: destination file

  public static void main(String[] args) {
    // source -> args[0]
    // detination -> args[1]

    try {

      BufferedReader inputStream = new BufferedReader(new FileReader(new File(args[0])));
      BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File(args[1]), false));

      String line = "";
      while ((line = inputStream.readLine()) != null) {
        outputStream.write(line+"\n");
      }
      inputStream.close();
      outputStream.close();

    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

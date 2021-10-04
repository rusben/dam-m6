package net.xeill.elpuig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CharsFileReader {

  public static void main(String[] args) {

    try {

      BufferedReader inputStream = new BufferedReader(new FileReader(new File("archivos/alphabeta5465.txt")));
      int c = inputStream.read();

      while (c != -1) {
        System.out.println((char) c);
        c = inputStream.read();
      }

      inputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}

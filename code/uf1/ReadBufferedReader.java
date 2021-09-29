package net.xeill.elpuig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class ReadBufferedReader {

  public static void main(String[] args) {

    try {

      BufferedReader inputStream = new BufferedReader(new FileReader(new File("treballadors.txt")));

      String line = "";

      while ((line = inputStream.readLine()) != null ) {
        System.out.println(line);
      }

      inputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}

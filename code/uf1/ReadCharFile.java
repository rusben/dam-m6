package net.xeill.elpuig;

import java.io.*;

public class ReadCharFile {

  public static void main(String[] args) {

    try {

      FileReader file = new FileReader("ReadCharFile.java");
      BufferedReader inputStream = new BufferedReader(file);

      boolean eof = false;

      while (!eof) {
        String line = inputStream.readLine();
        if (line == null) eof = true;
        else System.out.println(line);
      }
      inputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

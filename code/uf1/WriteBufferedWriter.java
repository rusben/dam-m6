package net.xeill.elpuig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class WriteBufferedWriter {

  public static void main(String[] args) {

    try {

      BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File("treballadors.txt"), true));

      for (int i = 0; i < 100; i++) {
        outputStream.write("Esta es la línea "+(i+1)+"\n");
      }

      outputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
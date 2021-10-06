package net.xeill.elpuig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ArrayToFile {
  public static void main(String[] args) {
    String[] text = {"Angel", "Estel", "Ona"};

    try {
      FileWriter fw = new FileWriter(System.getProperty("user.dir") + File.separator + "noms.txt", false);
      BufferedWriter bw = new BufferedWriter(fw);

      for (String t : text) {
        bw.write(t);
        bw.newLine();
      }
      bw.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

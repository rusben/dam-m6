package net.xeill.elpuig;

import java.io.*;

public class CopyArrayToFile {

  public static void main(String[] args) {
    String[] paraules = {"Angel", "Estel", "Ona"};
    File file = new File(System.getProperty("user.dir") + File.separator + "noms.txt");

    saveFile(paraules, file);
    showFile(file);
  }

  public static void saveFile(String[] paraules, File file) {
    try {
      FileWriter fw = new FileWriter(file, false);
      BufferedWriter bw = new BufferedWriter(fw);

      for (String t : paraules) {
        bw.write(t);
        bw.newLine();
      }
      bw.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void showFile(File file) {
    try {
      BufferedReader inputStream = new BufferedReader(new FileReader(file));

      String line = "";

      while ((line = inputStream.readLine()) != null) {
        System.out.println(line);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

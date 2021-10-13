package net.xeill.elpuig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CSVToScreen {

  static File f;

  public static void main(String[] args) {

    try {
      f = new File("src/archivos/personas.csv");
      readAndPrintRow(getColumns());

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public static String[] getColumns() {
    String line = "";
    try {

      BufferedReader inputStream = new BufferedReader(new FileReader(f));
      line = inputStream.readLine();
      inputStream.close();


    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return line.split(",");
  }

  public static void readAndPrintRow(String[] columns) {

    try {

      BufferedReader inputStream = new BufferedReader(new FileReader(f));

      String line = "";

      int i = 1;
      line = inputStream.readLine();
      String[] elementos;
      while ((line = inputStream.readLine()) != null ) {

        if (i != 1) System.out.println("****");
        elementos = line.split(",");

        System.out.println("Persona "+i);
        for (int j = 0; j < columns.length ; j++) {
          System.out.println(columns[j].trim()+": "+elementos[j].trim());
        }
        i++;
      }

      inputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}

package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TreballadorsToFile1 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    File fleExample = new File(System.getProperty("user.home")+File.separator+"Escriptori"+File.separator+"treballadors.txt");
    // Create that file and prepare to write some values to it
    try {
      PrintWriter pwInput = new PrintWriter(fleExample);

      String nom, cognom;
      int salari;
      boolean casat;

      boolean more = true;

      while (more) {
        System.out.print("nom? ");
        nom = in.nextLine();
        System.out.print("cognom? ");
        cognom = in.nextLine();
        System.out.print("salari? ");
        salari = in.nextInt();
        System.out.print("casat? ");
        casat = in.nextBoolean();
        in.nextLine(); // Para leer el enter que queda sin leer después del boolean

        System.out.println("Vols continuar?");

        pwInput.println(nom+","+cognom+","+salari+","+casat);

        if (!in.nextLine().equals("Y")) more = false;
      }
      pwInput.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
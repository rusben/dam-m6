package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreballadorsToScreen1 {
  public static void main(String[] args) {
    File fd = new File(System.getProperty("user.home") + File.separator + "Escriptori" + File.separator + "archivos/treballadors.txt");

    try {
      Scanner in = new Scanner(fd);

      int i = 1;
      while (in.hasNext()) {
        String line = in.nextLine();
        String[] parts = line.split(",");

        System.out.println("TREBALLADOR "+i);
        System.out.println();
        System.out.println(parts[0]);
        System.out.println();
        System.out.println(parts[1]);
        System.out.println();
        System.out.println(parts[2]);
        System.out.println();
        System.out.println(parts[3]);
        System.out.println();

        i++;
      }

    } catch (FileNotFoundException e) {
      System.out.println("Fichero no encontrado");
      System.out.println(e.getMessage());
    }
  }
}

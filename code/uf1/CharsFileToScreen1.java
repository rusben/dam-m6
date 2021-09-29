package net.xeill.elpuig;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CharsFileToScreen1 {
  public static void main(String[] args) {

    File fleExample = new File(System.getProperty("user.home") + File.separator + "Escriptori" + File.separator + "alphabeta.txt");

    try {
      Scanner in = new Scanner(fleExample);

      while (in.hasNext()) {
        System.out.print(in.nextLine().toUpperCase() + " ");
      }

    } catch (FileNotFoundException e) {
      System.out.println("Fichero no encontrado");
      System.out.println(e.getMessage());
    }

  }
}
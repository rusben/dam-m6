package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumsToFile1 {
  public static void main(String[] args) {
    //System.out.println("User home = " + System.getProperty("user.home"));
    //System.out.println(System.getProperty("user.home")+File.separator+"Escriptori"+File.separator+"Num11001200.txt");

    // Indicate that you are planning to use a file
    File fleExample = new File(System.getProperty("user.home")+File.separator+"Escriptori"+File.separator+"Num11001200.txt");
    try {
      // Create that file and prepare to write some values to it
      PrintWriter pwInput = new PrintWriter(fleExample);
      for (int i = 1100; i <= 1200; i++) {
        // Write a string to the file
        pwInput.println(i);
      }
      pwInput.close();
    } catch (FileNotFoundException e) {
      System.out.println("Fichero no encontrado");
      System.out.println(e.getMessage());
    }

  }
}

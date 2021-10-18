package net.xeill.elpuig;

import java.io.*;

public class CopyFilesBin {

  public static void main(String[] args)  {

    try {
      int data;
      DataInputStream input = new DataInputStream(new FileInputStream( "src/archivos/imagen.png"));
      DataOutputStream output = new DataOutputStream(new FileOutputStream("src/archivos/copia.png"));

        while ((data = input.read()) != -1) {
          output.write(data);
        }


    } catch(EOFException e) {
      System.out.println("Final de fichero.");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

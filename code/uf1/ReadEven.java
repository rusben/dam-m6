package net.xeill.elpuig;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;

public class ReadEven {

  public static void main(String[] args) {
    try {
      FileInputStream file = new FileInputStream("src/archivos/even.dat");
      DataInputStream data = new DataInputStream(file);

      int number;

      try {
        while (true) {
          number = data.readInt();
          System.out.println(number+" ");
        }
      } catch (EOFException e) {
        data.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

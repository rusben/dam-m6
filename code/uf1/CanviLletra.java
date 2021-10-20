package net.xeill.elpuig;

import java.io.*;

public class CanviLletra {

  public static void main(String[] args) {

    try {

      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/texto.txt"), "rw");
      char x = 'r';

      char c;
      int lectura;

      while ((lectura = randomAccessFile.read()) != -1) {
        try {

          c = (char) lectura;

          if (c == x) {
            randomAccessFile.seek(randomAccessFile.getFilePointer() - 1);
            //randomAccessFile.write(Byte.parseByte(String.valueOf(Character.toUpperCase(c))));
            randomAccessFile.writeByte(Character.toUpperCase(c));
          }
        } catch (EOFException e) {
          e.printStackTrace();

        }
      }
      randomAccessFile.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

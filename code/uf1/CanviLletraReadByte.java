package net.xeill.elpuig;

import java.io.*;

public class CanviLletraReadByte {

  public static void main(String[] args) {

    try {

      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/texto.txt"), "rw");
      char x = 'r';

      char c;
      int lectura;
      boolean fin = false;

      do {
        try {
          lectura = randomAccessFile.readByte();
          c = (char) lectura;

          if (c == x) {
            randomAccessFile.seek(randomAccessFile.getFilePointer() - 1);
            //randomAccessFile.write(Byte.parseByte(String.valueOf(Character.toUpperCase(c))));
            randomAccessFile.writeByte(Character.toUpperCase(c));
          }
        } catch (EOFException e) {
          fin = true;
          randomAccessFile.close();
          System.out.println("Hem acabat de llegir el fitxer.");
        }

      } while (!fin);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

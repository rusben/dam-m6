package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWritePosition {

  public static void main(String[] args) {
    /*
    Identificador: és un enter, ocupa 4 bytes
    Cognom: cadena de 10 caràcters. Com cada caràcter Unicode ocupa 2 bytes llavors el cognom ocupa 20 bytes
    Departament: és un enter, ocupa 4 bytes.
    Sou: és un double, ocupa 8 bytes.
    */

    String cognom = "Martin";
    StringBuffer bCognom = new StringBuffer(cognom);
    bCognom.setLength(10);

    int departament = 55;
    double salari = 5555.55;

    int position = 7;

    try {
      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/empleats.txt"), "rw");

      randomAccessFile.seek((position - 1) * 36);

      // Identificador
      randomAccessFile.writeInt(position);
      // String[10] amb el cognom
      randomAccessFile.writeChars(bCognom.toString());
      // Departament
      randomAccessFile.writeInt(departament);
      // Salari
      randomAccessFile.writeDouble(salari);

      randomAccessFile.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

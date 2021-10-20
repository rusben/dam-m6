package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWrite {

  public static void main(String[] args) {
    /*
    Identificador: és un enter, ocupa 4 bytes
    Cognom: cadena de 10 caràcters. Com cada caràcter Unicode ocupa 2 bytes llavors el cognom ocupa 20 bytes
    Departament: és un enter, ocupa 4 bytes.
    Sou: és un double, ocupa 8 bytes.
    */

    String[] cognoms = {"Parker", "Ocón", "Ben", "Orrit"};
    int[] departaments = {11, 22, 33, 44};
    double[] salaris = {1111.11, 2222.22, 3333.33, 4444.44};

    try {
      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/empleats.txt"), "rw");

      for (int i = 0; i < cognoms.length ; i++) {
        // Identificador
        randomAccessFile.writeInt(i+1);
        // String[10] amb el cognom
        StringBuffer sBuffer = new StringBuffer(cognoms[i]);
        sBuffer.setLength(10);
        randomAccessFile.writeChars(sBuffer.toString());
        // Departament
        randomAccessFile.writeInt(departaments[i]);
        // Salari
        randomAccessFile.writeDouble(salaris[i]);
      }
      randomAccessFile.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

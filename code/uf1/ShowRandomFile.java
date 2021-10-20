package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ShowRandomFile {

  /*
    Identificador: és un enter, ocupa 4 bytes
    Cognom: cadena de 10 caràcters. Com cada caràcter Unicode ocupa 2 bytes llavors el cognom ocupa 20 bytes
    Departament: és un enter, ocupa 4 bytes.
    Sou: és un double, ocupa 8 bytes.
  */

  public static void main(String[] args) {

    try {
      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/empleats.txt"), "r");

      int id, departament, position;
      double salari;
      String sCognom;
      char[] cognom;

      // Nos colocamos al incio del fichero
      position = 0;

      while (randomAccessFile.getFilePointer() != randomAccessFile.length()) {

        // Nos movemos a la posición del siguiente empleado que vamos a leer
        randomAccessFile.seek(position);

        // Identificador
        id = randomAccessFile.readInt();
        // Cognom (inicializamos)
        cognom = new char[10];
        // Cognom (lectura)
        for (int i = 0; i < cognom.length; i++) {
          cognom[i] = randomAccessFile.readChar();
        }
        sCognom = new String(cognom);

        // Departament
        departament = randomAccessFile.readInt();

        // Salari
        salari = randomAccessFile.readDouble();

        // Imprimimos
        System.out.println("Identificador: " + id);
        System.out.println("Cognom: " + sCognom);
        System.out.println("Departament: " + departament);
        System.out.println("Salari: " + salari);

        // Avanzamos la posición del puntero
        position += 36;
      }

      randomAccessFile.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

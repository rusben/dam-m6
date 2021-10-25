package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MyRandomWrite {

  // Les dades a inserir són: codi del departament i el nom departament.

  // Els noms dels departaments es llegeixen des d'un array i el seu
  // identificador serà (posició+1)*10 que ocuparà dins del fitxer per a cada valor.

  // La longitud del registre de cada departament és la mateixa 24 bytes i els
  // tipus que s'insereixen i la seva grandària en bytes és el següent:

  // codi: és un enter, ocupa 4 bytes
  // nom: cadena de 10 caràcters. Com cada caràcter Unicode ocupa 2 bytes llavors el cognom ocupa 20 bytes.

  public static void main(String[] args) {

    String[] departaments = {"Música", "Ciències", "Matemàtiques", "Informàtica", "Socials"};

    try {
      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/departaments.txt"), "rw");

      for (int i = 0; i < departaments.length; i++) {
        randomAccessFile.writeInt((i+1)*10);
        randomAccessFile.writeBytes(departaments[i]);

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
  }


  }
}

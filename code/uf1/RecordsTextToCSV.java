package net.xeill.elpuig;

import java.io.*;
import java.util.ArrayList;

public class RecordsTextToCSV {

  /*
Mihaita
Minca
21
****
Daniel
Barrueco
19
****
Ferran
Boutin
19

Generi un fitxer csv on la primera fila sigui nom,
cognoms, edat i les següents les ocurrències que
apareixen en el fitxer de lectura. Presuposem l'ús
de 3 arrays String[] per tal d'emmagatzemar temporalment
 les ocurrències aparegudes després de la lectura.
 Posteriorment es llegiran aquests tres arrays i
 s'enregisrarà el seu contingut en el fitxer csv
 de sortida. Es demana implementar tres mètodes.
 llegeixFitxer(), mostraPerPantalla() i escriuSortidaCSV()
  */

  static ArrayList<String> noms, cognoms, edat;


  public static void main(String[] args) {

    noms = new ArrayList<String>();
    cognoms = new ArrayList<String>();
    edat = new ArrayList<String>();



    llegeixFitxer(new File("src/archivos/entrada.txt"));
    mostraPerPantalla();
    escriuSortidaCSV(new File("src/archivos/salida.csv"));
  }


  public static void llegeixFitxer(File f) {
    try {
      BufferedReader inputStream = new BufferedReader(new FileReader(f));

      String line = "";

      while ((line = inputStream.readLine()) != null ) {
        noms.add(line);
        line = inputStream.readLine();
        cognoms.add(line);
        line = inputStream.readLine();
        edat.add(line);
        line = inputStream.readLine();
     //   if (line == null) break;
      }

      inputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void mostraPerPantalla() {
    System.out.println("nom, cognoms, edat");

    for (int i = 0; i < noms.size() ; i++) {
      System.out.print(noms.get(i));
      System.out.print(",");
      System.out.print(cognoms.get(i));
      System.out.print(",");
      System.out.print(edat.get(i));
      System.out.println();
    }
  }

  public static void escriuSortidaCSV(File f) {
    try {
      BufferedWriter outputStream = new BufferedWriter(new FileWriter(f, false));

      outputStream.write("nom, cognoms, edat");
      outputStream.newLine();

      for (int i = 0; i < noms.size() ; i++) {
        outputStream.write(noms.get(i));
        outputStream.write(",");
        outputStream.write(cognoms.get(i));
        outputStream.write(",");
        outputStream.write(edat.get(i));
        outputStream.newLine();
      }

      outputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}


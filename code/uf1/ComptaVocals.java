package net.xeill.elpuig;

import java.io.*;

public class ComptaVocals {

  public static void main(String[] args) {
    //System.out.println("Working Directory = " + System.getProperty("user.dir"));
    try {
      BufferedReader inputStream = new BufferedReader(new FileReader(new File("src/archivos/entradaVocals.txt")));
      BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File("src/archivos/sortidaVocals.csv")));

      String line = "";

      int a, e, i, o, u;

      outputStream.write("a,e,i,o,u");
      outputStream.newLine();

      while ((line = inputStream.readLine()) != null) {

        line = line.toLowerCase();
        a = e = i = o = u = 0;

        for (int j = 0; j < line.length(); j++) {
          switch (line.charAt(j)) {
            case 'a':
              a++;
              break;
            case 'e':
              e++;
              break;
            case 'i':
              i++;
              break;
            case 'o':
              o++;
              break;
            case 'u':
              u++;
              break;
            default:
              break;
          }
        }

        outputStream.write(a + "," + e + "," + i + "," + o + "," + u);
        outputStream.newLine();
      }

      inputStream.close();
      outputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

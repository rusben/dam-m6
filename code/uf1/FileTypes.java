package net.xeill.elpuig;

import java.io.*;

public class FileTypes {

  public static void main(String[] args) {
    writeFile("src/archivos/variables.txt");
    readFileBufferedReader("src/archivos/variables.txt");
    readFileDataInputStream("src/archivos/variables.txt");
    readFileCustomDataInputStream("src/archivos/variables.txt");
  }


  public static void readFileBufferedReader(String f) {
    System.out.println("Reading file "+f+" with BufferedReader");

    try {

      BufferedReader inputStream = new BufferedReader(new FileReader(new File(f)));

      String line = "";

      while ((line = inputStream.readLine()) != null) {
          System.out.println(line);
      }

      inputStream.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println("-------------------- BufferedReader");
  }

  public static void readFileDataInputStream(String f) {
    System.out.println("Reading file "+f+" with DataInputStream");
    try {
      int data;
      DataInputStream input = new DataInputStream(new FileInputStream( f));

      while ((data = input.read()) != -1) {
        System.out.print(data);
      }

    } catch(EOFException e) {
      System.out.println("Final de fichero.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("-------------------- DataInputStream");

  }

  public static void readFileCustomDataInputStream(String f) {
    System.out.println("Reading file "+f+" with CustomDataInputStream");
    try {
      int data;
      DataInputStream input = new DataInputStream(new FileInputStream( f));

      int a = input.readInt();
      boolean b = input.readBoolean();
      char c = input.readChar();
      double d = input.readDouble();
      byte[] e = input.readNBytes(9);
      float flo = input.readFloat();

      System.out.println(a);
      System.out.println(b);
      System.out.println(c);
      System.out.println(d);

      for (byte g : e) {
        System.out.print((char) g);
      }

      System.out.println(e);
      System.out.println(flo);

    } catch(EOFException e) {
      System.out.println("Final de fichero.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("-------------------- CustomDataInputStream");

  }

  public static void writeFile(String f) {
    try {
      DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(f));

      int a = 3;
      boolean b = true;
      char c = 'c';
      double d = 23444.33;
      String e = "DIRFISFOS";
      float flo = 3.33f;

      outputStream.writeInt(a);
      outputStream.writeBoolean(b);
      outputStream.writeChar(c);
      outputStream.writeDouble(d);
      outputStream.writeBytes(e);
      outputStream.writeFloat(flo);

      outputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

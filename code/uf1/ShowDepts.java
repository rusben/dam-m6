package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ShowDepts {

  public static void main(String[] args) {
    try {
      RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/archivos/departaments.txt"), "rw");

      

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


  }
}

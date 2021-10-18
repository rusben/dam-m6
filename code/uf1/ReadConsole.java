package net.xeill.elpuig;

import java.io.*;

public class ReadConsole {
  public static void main(String[] args) {
    InputStreamReader cin = null;

    try {
      cin = new InputStreamReader(System.in);
      System.out.println("Enter characters, 'q' to quit.");
      char c;
      do {
        c = (char) cin.read();
        System.out.print(c);
      } while (c != 'q');
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      if (cin != null) {
        try {
          cin.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

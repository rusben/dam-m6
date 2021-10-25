package net.xeill.elpuig;

import java.io.*;
import java.util.ArrayList;

public class CSVToObjectFile {

  public static File f;
  public static ArrayList<User> users = new ArrayList<User>();

  public static void main(String[] args) {
    f = new File("src/archivos/users.csv");

    loadUsers();
    writeUsers();

  }

  public static void loadUsers() {
    try {
      BufferedReader inputStream = new BufferedReader(new FileReader(f));

      String[] elementos;
      String line;

      while ((line = inputStream.readLine()) != null) {

        elementos = line.split(",");

        String username = elementos[0];
        String firstname = elementos[1];
        String lastname = elementos[2];
        String email = elementos[3];
        String password = elementos[4];

        User u = new User(username, firstname, lastname, email, password);
        System.out.println(u);
        users.add(u);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeUsers() {
    // users.bin

    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/archivos/users.bin"));

      for (User u : users) {
        objectOutputStream.writeObject(u);
      }

      objectOutputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

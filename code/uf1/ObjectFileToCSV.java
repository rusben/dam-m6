package net.xeill.elpuig;

import java.io.*;
import java.util.ArrayList;

public class ObjectFileToCSV {

  public static File f;
  public static ArrayList<User> users = new ArrayList<User>();

  public static void main(String[] args) {

    loadUsers();
    writeUsersToCSV();

  }

  public static void loadUsers() {
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("src/archivos/users.bin")));

      String[] elementos;
      String line;

      while (true) {
        User u = (User) objectInputStream.readObject();
        users.add(u);
      }


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (EOFException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void writeUsersToCSV() {
    // users.bin

    try {
      BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File("src/archivos/users2.csv")));

      for (User u : users) {

        outputStream.write(u.getUsername()+ "," +
                                 u.getFirstname()+ "," +
                                 u.getLastname()+ "," +
                                 u.getEmail()+ "," +
                                 u.getPassword()+"\n");


      }

      outputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

}

package net.xeill.elpuig;

import java.io.*;

public class ObjectStreamExample {

  public static class Person implements Serializable {
    public String name = null;
    public int age = 0;
  }

  public static void main(String[] args) {

    Person p = new Person();
    p.name = "John";
    p.age = 22;

    Person q = new Person();
    q.name = "Mary";
    q.age = 24;

    Person r, s ;

    r = s = null;

    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/archivos/person.bin"));
      objectOutputStream.writeObject(p);
      objectOutputStream.writeObject(q);
      objectOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/archivos/person.bin"));
      r = (Person) objectInputStream.readObject();
      s = (Person) objectInputStream.readObject();

      objectInputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(r.name +": "+ r.age);
    System.out.println(s.name +": "+ s.age);

  }
}

package net.xeill.elpuig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CustomObjectStreamExample {

  public static void main(String[] args) {



  }

  public static void introDades() throws IOException {


    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("person.bin"));
    Person p = new Person();
    p.setName("John");
    p.setAge(22);

    Person q = new Person();
    q.setName("Mary");
    q.setAge(24);


  }

  public static void mostraDades() {

  }
}

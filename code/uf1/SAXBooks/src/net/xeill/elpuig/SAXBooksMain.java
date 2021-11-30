package net.xeill.elpuig;

import java.io.File;

public class SAXBooksMain {
  public static void main(String[] args) {
    File file = new File("llibres.xml");
    BooksSAXParser booksSAXParser = new BooksSAXParser();
    booksSAXParser.openXMLWithSAX(file);
    System.out.println(booksSAXParser.getSAXInfo());
  }
}

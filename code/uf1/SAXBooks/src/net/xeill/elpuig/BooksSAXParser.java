package net.xeill.elpuig;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class BooksSAXParser {

  BookHandler bookHandler;
  SAXParser saxParser;
  File xmlFile;

  public int openXMLWithSAX(File file) {
    // Es crea un objecte SAXParser per interpretar el document XML
    try {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      saxParser = saxParserFactory.newSAXParser();
      // Es crea una instancia del Handler que serà el que recorri el document XML secuencialment
      bookHandler = new BookHandler();
      xmlFile = file;
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public String getSAXInfo() {
    // Es dona la sortida al parser per a que comenci a gestionar el document XML que
    // li passem com a paràmetre. Es recorrerà secuencialment el document XML i quan es
    // detecti un començament o un fi d'element o text llavors s'el tractarà (segons la implementació del handler)
    try {
      saxParser.parse(xmlFile, bookHandler);
      return bookHandler.getResultText();
    } catch (Exception e) {
      e.printStackTrace();
      return "Error processant el document XML";
    }
  }
}

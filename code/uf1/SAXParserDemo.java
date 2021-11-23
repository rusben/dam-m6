package net.xeill.elpuig;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParserDemo {
  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      SAXParser saxParser = saxParserFactory.newSAXParser();
      StudentHandler studentHandler = new StudentHandler();
      saxParser.parse(inputFile, studentHandler);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/*
input.txt

<?xml version="1.0"?>
<class>
  <student rollno="393">
    <firstname>Gerard</firstname>
    <lastname>Paulino</lastname>
    <nickname>gpaulino</nickname>
    <marks>85</marks>
  </student>
  <student rollno="493">
    <firstname>Adrià</firstname>
    <lastname>Lora</lastname>
    <nickname>alora</nickname>
    <marks>95</marks>
  </student>
  <student rollno="593">
    <firstname>Jose Luis</firstname>
    <lastname>Polonio</lastname>
    <nickname>jpolonio</nickname>
    <marks>90</marks>
  </student>
</class>
*/

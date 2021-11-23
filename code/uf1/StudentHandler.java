package net.xeill.elpuig;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StudentHandler extends DefaultHandler {

  boolean bFirstName = false;
  boolean bLastName = false;
  boolean bNickname = false;
  boolean bMarks = false;

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    super.startElement(uri, localName, qName, attributes);

    if (qName.equalsIgnoreCase("student")) {
      String rollNo = attributes.getValue("rollno");
      System.out.println("Roll No: "+rollNo);
    } else if (qName.equalsIgnoreCase("firstname")) {
      bFirstName = true;
    } else if (qName.equalsIgnoreCase("lastname")) {
      bLastName = true;
    } else if (qName.equalsIgnoreCase("nickname")) {
      bNickname = true;
    } else if (qName.equalsIgnoreCase("marks")) {
      bMarks = true;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    super.endElement(uri, localName, qName);

    if (qName.equalsIgnoreCase("student")) {
      System.out.println("End element: "+ qName);
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    super.characters(ch, start, length);

    if (bFirstName) {
      System.out.println("First name: "+ new String(ch, start, length));
      bFirstName = false;
    } else if  (bLastName) {
      System.out.println("Last name: "+ new String(ch, start, length));
      bLastName = false;
    } else if  (bNickname) {
      System.out.println("Nickname: "+ new String(ch, start, length));
      bNickname = false;
    } else if  (bMarks) {
      System.out.println("Marks: "+ new String(ch, start, length));
      bMarks = false;
    }
  }
}

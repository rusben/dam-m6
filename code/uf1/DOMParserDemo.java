package net.xeill.elpuig;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMParserDemo {
  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(inputFile);

      document.getDocumentElement().normalize();

      System.out.println("Root Element: " + document.getDocumentElement().getNodeName());

      NodeList nodeList = document.getElementsByTagName("student");
      System.out.println("-------------------------");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);

        System.out.println("\nCurrent Element: " + node.getNodeName());
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          System.out.println("Student roll no: " + element.getAttribute("rollno"));
          System.out.println("First name: " + element.getElementsByTagName("firstname").item(0).getTextContent());
          System.out.println("Last name: " + element.getElementsByTagName("lastname").item(0).getTextContent());
          System.out.println("Nickname: " + element.getElementsByTagName("nickname").item(0).getTextContent());
          System.out.println("Marks: " + element.getElementsByTagName("marks").item(0).getTextContent());

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

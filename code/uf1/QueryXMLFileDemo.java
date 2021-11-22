package net.xeill.elpuig;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class QueryXMLFileDemo {
  public static void main(String[] args) {
    try {
      File inputFile = new File("cars.xml");

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(inputFile);

      document.getDocumentElement().normalize();

      System.out.println("Root Element: " + document.getDocumentElement().getNodeName());

      NodeList nodeList = document.getElementsByTagName("supercars");
      System.out.println("-------------------------");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);

        System.out.println("\nCurrent Element: " + node.getNodeName());
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          System.out.println("Company: " + element.getAttribute("company"));

          NodeList carNameList = element.getElementsByTagName("carname");

          for (int j = 0; j < carNameList.getLength(); j++) {
            Node c = carNameList.item(j);

            if (c.getNodeType() == Node.ELEMENT_NODE) {
              Element car = (Element) c;
              System.out.println("car name: "+car.getTextContent());
              System.out.println("car type: "+car.getAttribute("type"));
            }
          }

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

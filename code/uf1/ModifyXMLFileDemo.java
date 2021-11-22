package net.xeill.elpuig;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ModifyXMLFileDemo {
  public static void main(String[] args) {
    try {

      File inputFile = new File("cars.xml");

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(inputFile);

      Node cars = document.getFirstChild();
      Node supercar = document.getElementsByTagName("supercars").item(0);

      // update supercar attribute
      NamedNodeMap namedNodeMap = supercar.getAttributes();
      Node nodeAttr = namedNodeMap.getNamedItem("company");
      nodeAttr.setTextContent("Lamborgini");

      // Loop the supercar child node
      NodeList nodeList = supercar.getChildNodes();

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          if ("carname".equals(element.getNodeName())) {
            if ("Ferrari 101".equals(element.getTextContent())) {
              element.setTextContent("Lamborgini 101");
            }
            if ("Ferrari 201".equals(element.getTextContent())) {
              element.setTextContent("Lamborgini 202");
            }
          }
        }
      }

      // Loop the cars childNodes
      NodeList childNodes = cars.getChildNodes();
      for (int i = 0; i < childNodes.getLength(); i++) {
        Node node = childNodes.item(i);
        if ("luxurycars".equals(node.getNodeName())) {
          cars.removeChild(node);
        }
      }

      // Write the content to console (testing)
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);

      System.out.println("-----------Modified File-----------");
      StreamResult consoleResult = new StreamResult(System.out);
      transformer.transform(domSource, consoleResult);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package net.xeill.elpuig;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXMLFileDemo {
  public static void main(String[] args) {
    try {

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();

      // Root element
      Element rootElement = document.createElement("FP");
      document.appendChild(rootElement);

      // grau element
      Element grauSuperior = document.createElement("CFGS");
      rootElement.appendChild(grauSuperior);

      // Setting attribute to element
      Attr attr = document.createAttribute("familia");
      attr.setValue("Informàtica");
      grauSuperior.setAttributeNode(attr);

      // Create element
      Element nouElement = document.createElement("cicle");
      Attr attrType = document.createAttribute("id");
      attrType.setValue("ASIX");
      nouElement.setAttributeNode(attrType);
      nouElement.appendChild(document.createTextNode("Administració de Sistemes Informàtics i Xarxes"));

      grauSuperior.appendChild(nouElement);

      // Another element
      Element altreElement = document.createElement("cicle");
      Attr attrTypo = document.createAttribute("id");
      attrTypo.setValue("DAM");
      altreElement.setAttributeNode(attrTypo);
      altreElement.appendChild(document.createTextNode("Desenvolupament d'Aplicacions Multiplataforma"));

      grauSuperior.appendChild(altreElement);

      // Write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File("cicles.xml"));

      transformer.transform(source, result);

      // Output to console (testing)
      StreamResult consoleResult = new StreamResult(System.out);
      transformer.transform(source, consoleResult);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

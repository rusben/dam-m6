package net.xeill.elpuig;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XPathExample {
  public static void main(String[] args) throws Exception {

    String fileName = "src/archivos/departaments.html";
    downloadFile(new URL("https://elpuig.xeill.net/departaments"), fileName);
    //Get DOM Node for XML
    Document document = getDocument(fileName);
    String xpathExpression = "";

    /*******Get attribute values using xpath******/

    //Get all employee ids
 //   xpathExpression = "//div[@class=\"navTreeItem\"]";

    //xpathExpression ="//li[contains(concat(' ', normalize-space(@class), ' '), ' navTreeItem ')]/a";


    xpathExpression = "//a";
    System.out.println(evaluateXPath(document, xpathExpression));
    System.out.println(evaluateXPathAndGetNodes(document, xpathExpression));

    NodeList nl = evaluateXPathAndGetNodes(document, xpathExpression);
    System.out.println(nl.getLength());

    for (int i = 0; i < nl.getLength(); i++) {
     System.out.println(nl.item(i).getNodeName());
    }

    //Get all employee ids in HR department
    //xpathExpression = "/employees/employee[department/name='HR']/@id";
    //System.out.println(evaluateXPath(document, xpathExpression));

    //Get employee id of 'Alex'
    //xpathExpression = "/employees/employee[firstName='Alex']/@id";
    //System.out.println(evaluateXPath(document, xpathExpression));

    //Get employee ids greater than 5
    //xpathExpression = "/employees/employee/@id[. > 5]";
    //System.out.println(evaluateXPath(document, xpathExpression));

    //Get employee whose id contains 1
    //xpathExpression = "/employees/employee[contains(@id,'1')]/firstName/text()";
    //System.out.println(evaluateXPath(document, xpathExpression));

    //Get employee whose id contains 1
    //xpathExpression = "descendant-or-self::*[contains(@id,'1')]/firstName/text()";
    //System.out.println(evaluateXPath(document, xpathExpression));
  }

  private static void downloadFile(URL url, String outputFileName) throws IOException {
    try (
          InputStream in = url.openStream();
          ReadableByteChannel rbc = Channels.newChannel(in);
          FileOutputStream fos = new FileOutputStream(outputFileName)
        ) {
          fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
  }

  private static NodeList evaluateXPathAndGetNodes(Document document, String xpathExpression) throws Exception {
    // Create XPathFactory object
    XPathFactory xpathFactory = XPathFactory.newInstance();

    // Create XPath object
    XPath xpath = xpathFactory.newXPath();

    try {
      // Create XPathExpression object
      XPathExpression expr = xpath.compile(xpathExpression);

      // Evaluate expression result on XML document
      NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
      return nodes;

    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception {
    // Create XPathFactory object
    XPathFactory xpathFactory = XPathFactory.newInstance();

    // Create XPath object
    XPath xpath = xpathFactory.newXPath();

    List<String> values = new ArrayList<>();
    try {
      // Create XPathExpression object
      XPathExpression expr = xpath.compile(xpathExpression);

      // Evaluate expression result on XML document
      NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

      for (int i = 0; i < nodes.getLength(); i++) {
        values.add(nodes.item(i).getNodeValue());
      }

    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return values;
  }

  private static Document getDocument(String fileName) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(fileName);
    return doc;
  }
}
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

    String localName = "src/archivos/departaments.html";
    downloadFile(new URL("https://elpuig.xeill.net/departaments"), localName);
    //Get DOM Node for XML
    Document document = getDocument(localName);

    // xpathExpression = "//div[@class=\"navTreeItem\"]";
    // xpathExpression ="//li[contains(concat(' ', normalize-space(@class), ' '), ' navTreeItem ')]/a";

    String xpathExpression = "//li[contains(concat(' ', normalize-space(@class), ' '), ' navTreeItem ')]/a";

   // System.out.println(evaluateXPath(document, xpathExpression));
   // System.out.println(evaluateXPathAndGetNodes(document, xpathExpression));

    NodeList nl = getNodesByExpression(document, xpathExpression);
    System.out.println(nl.getLength());

    for (int i = 0; i < nl.getLength(); i++) {
      System.out.println(nl.item(i).getTextContent().trim());
    }

    // https://howtodoinjava.com/java/xml/java-xpath-expression-examples/

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

  private static NodeList getNodesByExpression(Document document, String xpathExpression) throws Exception {
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

  /*
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
        values.add(nodes.item(i).getTextContent());
      }

    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return values;
  }
*/

  private static Document getDocument(String fileName) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // https://stackoverflow.com/questions/49790117/are-there-any-disadvantages-to-setnamespaceawaretrue
    // factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(fileName);

    return document;
  }

}


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DOMManager {

    private static DocumentBuilder builder;

    public DOMManager() {
      createDocumentBuilder();
    }

    public static void createDocumentBuilder () {
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      }
    }

  public static Document createDocumentFromFile (String filePath) {
    try {
      File file = new File(filePath);
      // Retorna el document
      return builder.parse(file);

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }

    return null;
  }

    public static Document createDocumentFromString (String sDocument) {
      try {
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(sDocument);
        ByteArrayInputStream input = null;
        input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));

        // Retorna el document
        return builder.parse(input);

      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SAXException e) {
        e.printStackTrace();
      }

      return null;
    }
  }

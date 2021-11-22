import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainDonParser {
  public static void main(String[] args) {
    try {
      DOMManager domManager = new DOMManager();
      Document doc = domManager.createDocumentFromFile("src/main/resources/input.xml");

      // normalizar
      doc.getDocumentElement().normalize();

      // Imprimimos el nombre del elemento root
      System.out.println("Root element: ");
      System.out.println(doc.getDocumentElement().getNodeName());

      // Lista de elementos con etiqueta student
      NodeList nodeList = doc.getElementsByTagName("student");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        System.out.println("Current element: " + node.getNodeName());

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          System.out.println("Student roll no: " + element.getAttribute("rollno"));
          System.out.println("First name: " + element.getElementsByTagName("firstname").item(1).getTextContent());
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

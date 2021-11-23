package net.xeill.elpuig;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GestorDOMExample {

  // Objecte Document que emmagatzema el DOM de l'XML seleccionat
  Document document;

  public int loadFileIntoDOM(File file) {
    try {
      document = null;
      // Es crea un objecte DocumentBuilderFactory
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      // Indica que el model DOM no ha de contemplar els comentaris que tingui el XML
      documentBuilderFactory.setIgnoringComments(true);
      // Ignora els espais en blanc que tingui el document
      // ATENCIÓ: Si no es fa això els espais en blanc apareixen com a nodes.
      documentBuilderFactory.setIgnoringElementContentWhitespace(true);
      // Es crea un objecte DocumentBuilder per carregar-hi l'estructura
      // d'arbre DOM a partir del XML seleccionat.
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      // Interpreta (parsea) el document XML (file) i genera el DOM equivalent
      document = documentBuilder.parse(file);
      // Ara document apunta a l'arbre DOM preparat per a ser recorregut
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int addBookToDOM(String titol, String autor, String any) {
    try {
      // Es creen els nodes en l'estructura DOM apuntada per la variable document
      // Es crea el node de tipus Element amb nom títol (<titol>)
      Node nodeTitol = document.createElement("Titol");
      // Es crea un node de tipus Text amb el titol del llibre
      Node nodeTitolText = document.createTextNode(titol);
      // S'afegeix el Node de Text amb el titol com a fill de l'Element Titol
      nodeTitol.appendChild(nodeTitolText);

      // Fem el mateix per a autor
      Node nodeAutor = document.createElement("Autor");
      Node nodeAutorText = document.createTextNode(autor);
      nodeAutor.appendChild(nodeAutorText);

      // Es crea un Node de tipus Element (<Llibre>)
      Node nodeLlibre = document.createElement("Llibre");
      // Al nou llibre se li afegeix un atribut publicat_en
      ((Element) nodeLlibre).setAttribute("publicat_en", any);
      // S'afegeix a llibre un Node de tipus Text amb un \n per a que en obrir-lo amb un
      // editor de text cada node apareixi en un línia diferent
      nodeLlibre.appendChild(document.createTextNode("\n"));
      // S'afegeix a llibre el node Titol
      nodeLlibre.appendChild(nodeTitol);
      nodeLlibre.appendChild(document.createTextNode("\n"));
      // S'afegeix a llibre el node Autor
      nodeLlibre.appendChild(nodeAutor);
      // Finalment s'obté el primer node del document i s'afegeix com a fill seu el node
      // llibre que ja té penjant tots els seus fills i atributs creats abans
      Node nodeArrel = document.getChildNodes().item(0);
      // Una altra forma de cacular el node arrel.
      // Node nodeArrel = document.getFirstChild();
      nodeArrel.appendChild(nodeLlibre);
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public String getDOMInfo() {
    String[] nodeData = null;
    String result = "";
    Node node;
    // Obté el primer node del DOM
    Node rootNode = document.getFirstChild();
    // Obté una llista de nodes amb tots els nodes fill
    NodeList nodeList = rootNode.getChildNodes();
    // Processa els nodes fills
    for (int i = 0; i < nodeList.getLength(); i++) {
      node = nodeList.item(i);
      // En executar pas a pas aquest codi , s'observa com els nodes que
      // troba són de tipus 1 (ELEMENT_NODE) i de tipus 3 (TEXT_NODE).
      // Això és perquè en DOM tot element que té un node fill TEXT encara que estigui en BLANC
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        // És un Node llibre que cal processar si és de tipus ELEMENT
        nodeData = processBook(node);

        result += "\n" + "Publicat l'any: " + nodeData[0];
        result += "\n" + "L'autor és: " + nodeData[1];
        result += "\n" + "El títol és: " + nodeData[2];
        result += "\n" + "--------------------";
      }
    }
    return result;
  }

  protected String[] processBook(Node node) {
    String[] data = new String[3];
    Node tempNode = null;
    int counter = 1;
    // Obté el primer atribut de la llista d'atributs que té el node
    // (en el nostre exemple només hi ha un atribut)
    data[0] = node.getAttributes().item(0).getNodeValue();
    // Obté els fills del Llibre (títol i autor)
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      tempNode = nodeList.item(i);
      // S'ha de comprovar que el tipus de Node que es vol tractar per que és
      // possible que hi hagi nodes de tipus TEXT que continguin
      // retorns de carro en canviar de línia en XML
      // En aquest exemple, quan es detecta un node que no és de tipus ELEMENT_NODE
      // és perquè és de tipus TEXT_NODE i conté els retorns de carro \n que
      // s'inclouen en el fitxer de text en crear el XML
      if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
        // IMPORTANT: per a obtenir el text amb el títol i l'autor s'accedeix al
        // TEXT_NODE fill del nodeActual i s'agafa el seu valor
        data[counter] = tempNode.getChildNodes().item(0).getNodeValue();
        counter++;
      }
    }
    return data;
  }

  public int saveDOMAsFile(File file) {

    // Write the content into an XML file
    try {
      // Prepare the transformation
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);
      StreamResult streamResult = new StreamResult(file);
      // Execute the transform
      transformer.transform(domSource, streamResult);

      // Output to console (testing)
      System.out.println("\n## DOM saved as file in: "+file.getPath()+"\n");
      StreamResult consoleResult = new StreamResult(System.out);
      transformer.transform(domSource, consoleResult);

      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {
    File inputFile = new File("booksInput.xml");
    File outpuFile = new File("booksOutput.xml");

    GestorDOMExample books = new GestorDOMExample();

    books.loadFileIntoDOM(inputFile);
    books.addBookToDOM("Parse XML files", "John Doe", "2021");

    System.out.println("## Printing DOM information:");
    System.out.println(books.getDOMInfo());

    books.saveDOMAsFile(outpuFile);
  }
}

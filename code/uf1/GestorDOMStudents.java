import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GestorDOMStudents {

    // Objecte Document que emmagatzema el DOM de l'XML seleccionat
    Document document;

    public void createEmptyDocument() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public int createRootElement(String tagName) {
        try {
            // Root element
            Element rootElement = document.createElement(tagName);
            document.appendChild(rootElement);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public Node createNode(String name, String content) {
        // Crea un node amb el nom name
        Node myNode = document.createElement(name);
        // Es crea un node de tipus Text amb el contigut del node
        Node nodeText = document.createTextNode(content);
        // S'afegeix el Node de Text amb el contingut com a fill de l'Element name
        myNode.appendChild(nodeText);

        return myNode;
    }

    public void afegeixNode(Node pare, Node fill) {
        // S'afegeix al node pare el fill
        pare.appendChild(fill);
    }

    public int addStudentToDOM(String DNI, String nom, String cognom1, String cognom2, String ciutat, String[] assignatures) {
        try {

            Node nodeDNI = createNode("DNI", DNI);
            Node nodeNom = createNode("Nom", nom);
            Node nodeCognom1 = createNode("Cognom1", cognom1);
            Node nodeCognom2 = createNode("Cognom2", cognom2);
            Node nodeCiutat = createNode("Ciutat", ciutat);

            // Es crea un Node de tipus Element (<Alumne>)
            Node nodeAlumne = document.createElement("Alumne");
            afegeixNode(nodeAlumne, nodeDNI);
            afegeixNode(nodeAlumne, nodeNom);
            afegeixNode(nodeAlumne, nodeCognom1);
            afegeixNode(nodeAlumne, nodeCognom2);
            afegeixNode(nodeAlumne, nodeCiutat);

            Node nodeAssignatures = afegeixAssignatures(nodeAlumne, assignatures);
            afegeixNode(nodeAlumne, nodeAssignatures);

            // Finalment s'obté el primer node del document i s'afegeix com a fill seu el node
            // llibre que ja té penjant tots els seus fills i atributs creats abans
            Node nodeArrel = document.getChildNodes().item(0);
            // Una altra forma de cacular el node arrel.
            // Node nodeArrel = document.getFirstChild();
            nodeArrel.appendChild(nodeAlumne);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private Node afegeixAssignatures(Node nodeAlumne, String[] assignatures) {
        // Crea el node Assignatures
        Node nodeAssignatures = document.createElement("Assignatures");
        for (String s : assignatures) {
            Node nodeAssignatura = createNode("Assignatura", s);
            afegeixNode(nodeAssignatures, nodeAssignatura);
        }
        return nodeAssignatures;
    }

    public int saveDOMAsFile(File file) {

        // Write the content into an XML file
        try {
            // Prepare the transformation
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Función para indentar
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            // Execute the transform
            transformer.transform(domSource, streamResult);

            // Output to console (testing)
            System.out.println("\n## DOM saved as file in: " + file.getPath() + "\n");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(domSource, consoleResult);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        File outpuFile = new File("studentsOutput.xml");

        GestorDOMStudents studentsManager = new GestorDOMStudents();
        studentsManager.createEmptyDocument();
        studentsManager.createRootElement("Alumnes");
   
        studentsManager.addStudentToDOM("123456789", "John", "Doe", "Foo", "Andorra la Vella", new String[]{"Mates", "Cata"});
        studentsManager.addStudentToDOM("987654321", "Paco", "Jones", "Me", "Barcelona", new String[]{"Natus", "English"});

        System.out.println("## Printing DOM information:");
        studentsManager.saveDOMAsFile(outpuFile);
    }
}

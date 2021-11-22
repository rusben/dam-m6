* [Processament de fitxers XML](#processament-fitxers-xml)
* [Analitzadors sintàctics o parsers](#analitzadors-sintactics-parsers)
* [La llibrería DOM](#llibreria-dom)
  * [Estructura del DOM](#estructura-dom)
  * [Analitzar un document amb DOM Parser](#analitzar-amb-dom-parser)
  * [Import XML-related packages](#import-packages)
  * [Create a DocumentBuilder](#create-documentbuilder)
  * [Create a Document from a File or Stream](#create-document-file-stream)
  * [Extract the root element](#extract-root-element)
  * [Examine attributes](#examine-attributes)
  * [Examine sub-elements](#examine-subelements)
* [Examples DOM](#examples-dom)
  * [Parsing a document using DOM Parser](#example-parsing-document-dom)
  * [DOM Parser - Query XML Document](#example-query-xml-document-dom)
  * [DOM Parser - Create XML Document](#example-create-xml-document-dom)
  * [DOM Parser - Modify XML Document](#example-modify-xml-document-dom)
  * [Using methods to DOM parsing](#example-using-methods-to-dom-parsing)
* [Exercicis DOM](#exercicis-dom)

* [La llibrería SAX](#llibreria-sax)
* [Examples SAX](#examples-sax)
  * [Parse XML Document using SAX Parser](#example-parsing-document-sax)
  * [Open and loop XML using SAX Parser](#example-open-loop-document-sax)
* [Exercicis SAX](#exercicis-sax)



# Processament de fitxers XML <a name="processament-fitxers-xml"></a>
Sovint ens pot interessar emmagatzemar objectes sencers per poder-los recuperar en qualsevol moment. Aquí veurem bàsicament dues maneres diferents d'aconseguir fer-los persistents.

1. Podem seriar-los usant les eines específiques que `Java` té per convertir qualsevol objecte en una sèrie de bytes.
2. També podem convertir els objectes persistents en una jerarquia `XML` capaç d'emmagatzemar les dades bàsiques i les relacions establertes entre objectes.

En el primer cas, ja hem vist com fer servir la interfície `Serializable`. Recordem que per tal que un objecte pugui ser seriat cal que la seva classe i tot el seu contingut implementin la interfície `Serializable`. Es tracta d'una interfície sense mètodes, perquè l'únic objectiu de la interfície és actuar de marcador per indicar a la màquina virtual quines classes es poden seriar i quines no.

Ara veurem com convertir els objectes persistents en una jerarquia `XML` capaç d'emmagatzemar les dades bàsiques i les relacions establertes entre objectes. Quan es volen emmagatzemar dades que hagin de ser llegides per aplicacions executades en múltiples plataformes serà necessari recórrer a formats més estandarditzats, com ara els llenguatges de marques.

Els documents `XML` aconsegueixen estructurar la informació intercalant un seguit de marques anomenades etiquetes. En `XML`, una etiqueta pot contenir altres etiquetes o bé informació textual. D'aquesta manera, aconseguirem subdividir la informació estructurant-la de forma que pugui ser fàcilment interpretada.

El llenguatge `XML` (`eXtended Markup Language`) té molta utilitat:

* Representa la informació de forma neutra, independent del llenguatge de programació i del sistema operatiu emprat.
* És útil en el desenvolupament d'aplicacions.
* S'han dissenyat moltes tecnologies gràcies a les possibilitats ofertes per `XML`, un exemple d'elles són els serveis web.
* Des d'un punt de vista a "baix nivell", un document `XML` no és altra cosa que un fitxer de text. Realment res no impedeix utilitzar llibreries d'accés a fitxers, com les vistes en la secció anterior, per accedir i manipular fitxers `XML`.

No obstant això, des d'un punt de vista a "alt nivell" `XML` no és un fitxer de text qualsevol. Calen eines específiques (llibreries) per accedir i manipular aquest tipus d'arxius de manera potent, flexible i eficient. Així:

* Es redueix els temps de desenvolupament d'aplicacions.
* S'optimitzen els propis accessos a `XML`.
* Es pot manejar els documents `XML` de forma simple i sense carregar innecessàriament el sistema.

# Analitzadors sintàctics o parsers <a name="analitzadors-sintactics-parsers"></a>
Les eines que llegeixen el llenguatge `XML` i comproven si el document és vàlid sintàcticament s'anomenen analitzadors sintàctics o parsers. Un parser `XML` és un mòdul, biblioteca o programa encarregat de transformar el fitxer de text en un model intern que optimitza el seu accés. Per `XML` ha un gran nombre de parsers o analitzadors sintàctics disponibles per dos dels models més coneguts: `DOM` i `SAX`, encara que existeixen molts altres.

Una classificació de les eines per a l'accés a fitxers `XML` és la següent:

* Eines que validen els documents `XML`. Aquestes comproven que el document `XML` al qual es vol accedir està ben conformat (well-formed) segons la definició de `XML` i, a més, que és vàlid respecte a un esquema `XML` (`XML-Schema`). Un exemple d'aquest tipus d'eines és `JAXB` (`Java Architecture for XML Binding`).

* Eines que no validen els documents `XML`. Aquestes només comproven que el document està ben conformat segons la definició `XML`, però no necessiten d'un esquema associat per comprovar si és vàlid pel que fa a aquest esquema. Exemples d'aquest tipus d'eines són `DOM` i `SAX`.

A continuació es descriu l'accés a dades amb les tecnologies més esteses: `DOM`, `SAX` i `JAXB`.

https://zetcode.com/java/dom/

## La llibrería DOM <a name="llibreria-dom"></a>

`DOM` és un analitzador jeràrquic que guarda totes les dades de `XML` en memòria dins una estructura jeràrquica. És ideal per a aplicacions que requereixin una consulta contínua de les dades.

El format de l'estructura on s'emmagatzema la informació a la memòria `RAM` ha estat especificat per l'organisme internacional `W3C` (`World Wide Web Consortium`) i s'acostuma a conèixer com a `DOM` (`Document Object Model`). És una estructura que `HTML` i `Javascript` han popularitzat molt i es tracta d'una especificació que Java materialitza en forma d'interfícies. La principal s'anomena Document i representa tot un document `XML`.

L'estàndard `W3C` defineix l'especificació de la classe `DocumentBuilder` amb el propòsit de poder instanciar estructures `DOM` a partir d'un `XML`. La classe `DocumentBuilder` és una classe abstracta, i per tal que es pugui adaptar a les diferents plataformes, pot necessitar fonts de dades o requeriments diversos. Recordeu que les classes abstractes no es poden instanciar de forma directa. Per aquest motiu, el consorci `W3C` especifica també la classe `DocumentBuilderFactory`.

Les instruccions necessàries per llegir un fitxer `XML` i crear un objecte Document serien les següents:

```java
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse(new File("fitxer.xml"));
```

`DocumentBuilder` també instancia objectes `Document` buits que podran ser manipulats a posteriori.

```java
DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
Document doc = docBuilder.newDocument();
```

L'escriptura de la informació continguda al `DOM` es pot seqüenciar en forma de text fent servir una altra utilitat de `Java` anomenada `Transformer`. Es tracta d'una utilitat que permet realitzar fàcilment conversions entre diferents representacions d'informació jeràrquica. És capaç, per exemple, de passar la informació continguda en un objecte `Document` a un fitxer de text en format `XML`. També seria capaç de fer l'operació inversa, però el mateix `DocumentBuilder` ja s'encarrega d'això.

`Transformer` és també una classe abstracta i requereix d'una factory per poder ser instanciada. La classe `Transformer` pot treballar amb multitud de contenidors d'informació perquè en realitat treballa amb un parell de tipus adaptadors (classes que fan compatibles jerarquies diferents) que s'anomenen `Source` i `Result`. Les classes que implementin aquestes interfícies s'encarregaran de fer compatible un tipus de contenidor específic al requeriment de la classe `Transformer`.

El codi bàsic per realitzar una transformació de `DOM` a fitxer de text `XML` seria el següent:

```java
// Creació d'una instancia Transformer
Transformer transformer = TransformerFactory.newInstance().newTransformer();
// Creació dels adaptadors Source i Results a partir d'un Document i un File.
StreamResult result = new StreamResult(file);
DOMSource source = new DOMSource(doc);
transformer.transform(source, result);
```

Els programes `Java` que utilitzen `DOM` necessiten aquestes interfícies:

* ***`Document`***: És un objecte que equival a un exemplar d'un document `XML`. Permet crear nous nodes en el document.
* ***`Element`***: Cada element del document `XML` té un equivalent en un objecte d'aquest tipus. Exposa propietats i mètodes per manipular els elements del document i els seus atributs.
* ***`Node`***: Representa qualsevol node del document.
* ***`Nodelist`***: Conté una llista amb els nodes fills d'un node.
* ***`Attr`***: Permet accedir als atributs d'un node.
* ***`Text`***: Són les dades caràcter d'un element.
* ***`CharacterData`***: Representa les dades caràcter presents en el document.
* ***`DocumentType`***: Proporciona informació continguda en l'etiqueta `<! DOCTYPE>`

En resum, la tecnologia `DOM` (`Document Object Model`) és una interfície de programació que permet analitzar i manipular dinàmicament i de manera global el contingut, l'estil i l'estructura d'un document.
Per treballar amb un document `XML` primer s'emmagatzema en memòria en forma d'arbre amb nodes pare, nodes fill i nodes finals que són aquells que no tenen descendents.

Un cop creada en memòria aquesta estructura, els mètodes de `DOM` permeten recórrer els diferents nodes de l'arbre i analitzar a quin tipus particular pertanyen. En funció del tipus de node, la interfície ofereix una sèrie de funcionalitats o altres per poder treballar amb la informació que contenen.

`DOM` ofereix una manera d'accedir a documents `XML` tant per ser llegit com per ser modificat. El seu únic inconvenient és que a l'arbre `DOM` es crea tot en memòria principal, de manera que si el document `XML` és molt gran, la creació i manipulació de `DOM` seria intractable.
Per poder treballar amb `DOM` en `Java` necessitem les classes i interfícies que componen el paquet `org.w3c.dom` i el paquet `javax.xml.parsers`. Aquestes classes ofereixen mètodes per carregar documents des d'una font de dades.

Conté dues classes fonamentals:

* `DocumentBuilder`
* `DocumentBuilderFactory`

`DOM` no defineix cap mecanisme per generar un fitxer `XML` a partir d'un arbre DOM. Per això farem servir el paquet `javax.xml.transform` que permet especificar una font i un resultat.

### Estructura DOM <a name="estructura-dom"></a>
L'estructura `DOM` pren la forma d'un arbre, on cada part de `XML` s'hi trobarà representada en forma de node. En funció de la posició en el document `XML`, parlarem de diferents tipus de nodes. El node principal que representa tot un `XML` sencer s'anomena `document`, i les diverses etiquetes, inclosa l'etiqueta arrel, es coneixen com a nodes element. El contingut textual d'una etiqueta s'instancia com a node de tipus `TextElement` i els atributs com a nodes de tipus `Attribute`.


Cada node específic disposa de mètodes per accedir a les seves dades concretes (nom, valor, nodes fills, node pare, etc.).

El `DOM` resultant obtingut des d'un `XML` acaba sent un còpia exacta del fitxer, però disposat de diferent manera. Tant al `XML` com al `DOM` hi haurà informació no visible, com ara els retorns de carro, que cal tenir en compte per tal de saber processar correctament el contingut i evitar sorpreses poc comprensibles.

La interfície `Document` contempla un conjunt de mètodes per seleccionar diferents parts de l'arbre a partir del nom de l'etiqueta o d'un atribut identificador. Les parts de l'arbre es retornen com a objectes `Element`, els quals representen un node i tots els seus fills. D'aquesta manera, podrem anar explorant parts de l'arbre sense necessitat d'haver de passar per tots el nodes.

Els objectes Element disposen de mètodes per afegir nous fills (`appendChild`) o assignar el valor a un atribut (`setAtributte`). També permeten la consulta del valor dels atributs (`getAttributte`) o la navegació pels nodes de l'arbre (`getParentNode`, per obtenir el pare; `getFirstChild | getLastChild`, per obtenir el primer | darrer fill, o `getNextSibling` per navegar de germà en germà).

L'objecte `Document` farà de factory per a qualsevol node del document. La creació de nous elements (etiquetes) implicarà l'ús de `createElement`. Si volem crear contingut textual caldrà cridar el mètode `createTextNode`, i si el que volem són comentaris cridarem `createComment`.

La creació de nodes no implica la ubicació d'aquest dins l'arbre. És a dir, a més de crear-los caldrà assignar-los a un pare usant `appendChild`.

### Analitzar un document amb DOM Parser <a name="analitzar-amb-dom-parser"></a>

A continuació es mostren els passos que s'utilitzen per analitzar un document amb `DOM Parser`.

* Importa els paquets relacionats amb `XML`.
* Crea un `DocumentBuilder`.
* Crea un document a partir d'un fitxer o flux.
* Extreu l'element arrel.
* Examina els atributs.
* Examina els subelements.

#### Import XML-related packages <a name="import-packages"></a>
```java
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
```

#### Create a DocumentBuilder <a name="create-documentbuilder"></a>
```java
DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
```

#### Create a Document from a File or Stream <a name="create-document-file-stream"></a>
```java
StringBuilder xmlStringBuilder = new StringBuilder();
xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
ByteArrayInputStream input = new ByteArrayInputStream( xmlStringBuilder.toString().getBytes("UTF-8"));
Document doc = builder.parse(input);
```

#### Extract the root element <a name="extract-root-element"></a>
```java
Element root = document.getDocumentElement();
```

#### Examine attributes <a name="examine-attributes"></a>
```java
// Returns specific attribute
getAttribute("attributeName");
// Returns a Map (table) of names/values
getAttributes();
```

#### Examine sub-elements <a name="examine-subelements"></a>
```java
// Returns a list of subelements of specified name
getElementsByTagName("subelementName");
// Returns a list of all child nodes
getChildNodes();
```

### Examples DOM <a name="examples-dom"></a>
#### Example: Parsing a document using DOM <a name="example-parsing-document-dom"></a>
Here is the input xml file we need to parse:

```xml
<?xml version="1.0"?>
<class>
  <student rollno="393">
    <firstname>Gerard</firstname>
    <lastname>Paulino</lastname>
    <nickname>gpaulino</nickname>
    <marks>85</marks>
  </student>
  <student rollno="493">
    <firstname>Adrià</firstname>
    <lastname>Lora</lastname>
    <nickname>alora</nickname>
    <marks>95</marks>
  </student>
  <student rollno="593">
    <firstname>Jose Luis</firstname>
    <lastname>Polonio</lastname>
    <nickname>jpolonio</nickname>
    <marks>90</marks>
  </student>
</class>
```

Here is the code we execute:

```java
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

```

This would produce the following result:

~~~
Root Element: class
-------------------------

Current Element: student
Student roll no: 393
First name: Gerard
Last name: Paulino
Nickname: gpaulino
Marks: 85

Current Element: student
Student roll no: 493
First name: Adrià
Last name: Lora
Nickname: alora
Marks: 95

Current Element: student
Student roll no: 593
First name: Jose Luis
Last name: Polonio
Nickname: jpolonio
Marks: 90
~~~

#### Example: DOM Parser - Query XML Document <a name="example-query-xml-document-dom"></a>

Here is the input xml file we need to query:

```xml
<?xml version="1.0"?>
<cars>
    <supercars company="Ferrari">
        <carname type="formula one">Ferrari 101</carname>
        <carname type="sports car">Ferrari 201</carname>
        <carname type="sports car">Ferrari 301</carname>
    </supercars>
    <supercars company="Lamborgini">
        <carname>Lamborgini 001</carname>
        <carname>Lamborgini 002</carname>
        <carname>Lamborgini 003</carname>
    </supercars>
    <luxurycars company="Benteley">
        <carname>Benteley 1</carname>
        <carname>Benteley 2</carname>
        <carname>Benteley 3</carname>
    </luxurycars>
</cars>
```

```java
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
```

This would produce the following result:
~~~
Root Element: cars
-------------------------

Current Element: supercars
Company: Ferrari
car name: Ferrari 101
car type: formula one
car name: Ferrari 201
car type: sports car
car name: Ferrari 301
car type: sports car

Current Element: supercars
Company: Lamborgini
car name: Lamborgini 001
car type:
car name: Lamborgini 002
car type:
car name: Lamborgini 003
car type:
~~~

#### Example: DOM Parser - Create XML Document <a name="example-create-xml-document-dom"></a>
Here is the XML we need to create:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<FP>
<CFGS familia="Informatica">
<cicle id="ASIX">Administració de Sistemes Informàtics i Xarxes</cicle>
<cicle id="DAM">Desenvolupament d'Aplicacions Multiplataforma</cicle>
</CFGS>
</FP>
```

```java
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

```

#### Example: DOM Parser - Modify XML Document <a name="example-modify-xml-document-dom"></a>
Here is the input xml file we need to modify:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<cars>
   <supercars company="Ferrari">
      <carname type="formula one">Ferrari 101</carname>
      <carname type="sports">Ferrari 202</carname>
   </supercars>
   <luxurycars company="Benteley">
      <carname>Benteley 1</carname>
      <carname>Benteley 2</carname>
      <carname>Benteley 3</carname>
   </luxurycars>
</cars>
```

```java
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

```

~~~
-----------Modified File-----------
<?xml version="1.0" encoding="UTF-8" standalone="no"?><cars>
    <supercars company="Lamborgini">
        <carname type="formula one">Lamborgini 101</carname>
        <carname type="sports car">Lamborgini 202</carname>
        <carname type="sports car">Ferrari 301</carname>
    </supercars>
    <supercars company="Lamborgini">
        <carname>Lamborgini 001</carname>
        <carname>Lamborgini 002</carname>
        <carname>Lamborgini 003</carname>
    </supercars>
</cars>
~~~


#### Example: Using methods to DOM parsing <a name="example-using-methods-to-dom-parsing"></a>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Llibres>
<Llibre publicat_en="1922">
<Titol>Siddharta</Titol>
<Autor>Hermann Hesse</Autor>
</Llibre>
<Llibre publicat_en="1985">
<Titol>El perfum</Titol>
<Autor>Patrick Süskind</Autor>
</Llibre>
<Llibre publicat_en="1947">
<Titol>Diari d'Anna Frank</Titol>
<Autor>Anne Frank</Autor>
</Llibre>
</Llibres>
```

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><Llibres>
<Llibre publicat_en="1922">
<Titol>Siddharta</Titol>
<Autor>Hermann Hesse</Autor>
</Llibre>
<Llibre publicat_en="1985">
<Titol>El perfum</Titol>
<Autor>Patrick Süskind</Autor>
</Llibre>
<Llibre publicat_en="1947">
<Titol>Diari d'Anna Frank</Titol>
<Autor>Anne Frank</Autor>
</Llibre>
```

### Exercicis <a name="exercicis-dom"></a>
#### Exercici 1
Utilitzar el codi de l'exemple per crea-ne una classe anomenada `AlumnesDOM1.java`  que crei un fitxer `XML` amb `Alumnes`, que guardi els elements de cada `Alumne` que conté els nodes `<DNI>`, `<Nom>`, `<Cognom1>`, `<Cognom2>`, `<Ciutat>` i `<Assignatures>` en el node arrel anomenat `Alumnes`, així com el valor de l'atribut `Edat` de cada `<Alumne>`.

#### Exercici 2
Adaptar el codi de l'exemple pel cas de l'exercici d'accés aleatori. S'ha de treballar amb les dades inserides en aquell exercici, `ID`, `cognom`, `departament` i `sou`. Es demana implementar la classe `EmpleatsDOM1.java` codi per crear un fitxer `XML` anomenat `empleats.xml`.

#### Exercici 3
Implementar un altre classe anomenada `EmpleatsDOM2.java` per llegir aquest `XML` creat i ensenyar els registres per pantalla (especificant les dades, per exemple "`ID: XX,Cognom: XXXX, Departament: XX, Sou: XXXX`").

## La llibrería SAX <a name="llibreria-sax"></a>

`SAX` és un analitzador sintàctic, al contrari de `DOM` que requereix memòria i temps sobretot si el fitxer `XML` a processar és bastant gran i complex, `SAX` analitza el fitxer a la vegada que el llegeix. Una alternativa en aquests casos son els analitzadors seqüencials, que permeten extreure el contingut a mida que es van descobrint les etiquetes d’obertura i tancament (analitzadors sintàctics). Són analitzadors molt ràpids, però presenten el problema que cada cop que es necessita accedir a una part del contingut cal rellegir tot el document de dalt a baix.

En `Java`, l’analitzador sintàctic més popular s’anomena `SAX`, que és l’acrònim de Simple `API` for XML. Els analitzadors sintàctics són capaços d’aïllar les dades `XML` en una sola lectura seqüencial detectant les etiquetes d’obertura i tancament. Són molt ràpids, però han de llegir tot el document a cada consulta.

Doncs, `SAX` és una altra tecnologia per poder accedir a `XML` des de llenguatges de programació i encara que `SAX` tingui el mateix objectiu que `DOM`, aquesta tecnologia aborda el problema des d'una òptica diferent. En general es fa servir `SAX` quan la informació emmagatzemada en els documents XML és clara, està ben estructurada i no necessita ser modificada.


Les principals característiques de `SAX` són:

* `SAX` ofereix una alternativa per llegir documents `XML` de manera seqüencial. El document només es llegeix una vegada. A diferència de `DOM`, el programador no es pot moure pel document al seu gust. Una vegada que s'obre el document, es recorre seqüencialment el fitxer fins al final.
* `SAX`, a diferència de `DOM`, no carrega el document en memòria, sinó que el llegeix directament des del fitxer. Això el fa especialment útil quan el fitxer `XML` és molt gran.

SAX segueix els següents passos bàsics:

1. Se li diu al parser `SAX` quin fitxer vol que sigui llegit de manera seqüencial.
2. El document `XML` és traduït a una sèrie d'esdeveniments.
3. Els esdeveniments generats poden controlar-se amb mètodes de control anomenats `callbacks`.
4. Per implementar els callbacks n'hi ha prou amb implementar la interfície `ContentHandler` (la seva implementació per defecte és `DafaultHandler`).

El procés es pot resumir de la següent manera:
1. `SAX` obre un fitxer `XML` i posa un punter a l'inici del mateix.
2. Quan comença a llegir el fitxer, el punter va avançant seqüencialment.
3. Quan `SAX` detecta un element propi del `XML` llavors llança un esdeveniment. Un esdeveniment pot ser causat per:

 * Que `SAX` hagi detectat el començament del document `XML`.
 * Que s'hagi detectat al final del document `XML`.
 * Que s'hagi detectat una etiqueta d'inici d'un element, per exemple `<llibre>`.
 * Que s'hagi detectat una etiqueta de final d'un element, per exemple `</llibre>`.
 * Que s'hagi detectat un atribut.
 * Que s'hagi detectat una cadena de caràcters que pot ser un text.
 * Que s'hagi detectat un error.

Quan `SAX` retorna que ha detectat un esdeveniment, llavors aquest esdeveniment pot ser manejat amb la classe `DefaultHandler` (callbacks). Aquesta classe pot ser estesa i els mètodes d'aquesta classe
poden ser redefinits (sobrecarregats) pel programador per aconseguir l'efecte desitjat quan `SAX` detecta els esdeveniments. Per exemple, es pot redefinir el mètode `public void startElement()`, que és el que s'invoca quan `SAX` detecta un esdeveniment de començament d'un element. Com a exemple, la redefinició d'aquest mètode pot consistir en comprovar el nom del nou element detectat, i si és un en concret llavors treure per pantalla un missatge amb el seu contingut.

Quan `SAX` detecta un esdeveniment d'error o un final de document llavors s'acaba el recorregut.

## SAX vs DOM <a name="sax-vs-dom"></a>
És millor `DOM` quan:
* Es requereix modificar l'estructura de l'`XML`.
* Es comparteix el document en memòria amb altres aplicacions.
* La mida del document no és molt gran.

És millor `SAX` quan:
* La tasca a realitzar requereix molta memòria i alt rendiment.
* No cal recórrer l'estructura completa del document.
* Cal anar processant els elements de l'arxiu a mesura que van arribant.

https://ca.wikipedia.org/wiki/Simple_API_for_XML

https://www.tutorialspoint.com/java_xml/java_sax_parser.htm

https://www.vogella.com/tutorials/JavaXML/article.html

http://cafeconleche.org/books/xmljava/chapters/index.html


### Examples SAX <a name="examples-sax"></a>
#### Parse XML Document using SAX Parser <a name="example-parsing-document-sax"></a>

```xml
<?xml version="1.0"?>
<class>
<student rollno="393">
<firstname>Gerard</firstname>
<lastname>Paulino</lastname>
<nickname>gpaulino</nickname>
<marks>85</marks>
</student>
<student rollno="493">
<firstname>Adrià</firstname>
<lastname>Lora</lastname>
<nickname>alora</nickname>
<marks>95</marks>
</student>
<student rollno="593">
<firstname>Jose Luis</firstname>
<lastname>Polonio</lastname>
<nickname>jpolonio</nickname>
<marks>90</marks>
</student>
</class>
```

~~~
Roll No : 393
First Name: Gerard
Last Name: Paulino
Nick Name: gpaulino
Marks: 85
End Element :student
Roll No : 493
First Name: Adrià
Last Name: Lora
Nick Name: alora
Marks: 95
End Element :student
Roll No : 593
First Name: Jose Luis
Last Name: Polonio
Nick Name: jpolonio
Marks: 90
End Element :student
~~~

#### Open and loop XML using SAX Parser <a name="example-open-loop-document-sax"></a>


Obrir i Recórrer XML amb SAX des de Java

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Llibres>
<Llibre publicat_en="1922">
<Titol>Siddharta</Titol>
<Autor>Hermann Hesse</Autor>
</Llibre>
<Llibre publicat_en="1985">
<Titol>El perfum</Titol>
<Autor>Patrick Süskind</Autor>
</Llibre>
<Llibre publicat_en="1947">
<Titol>Diari d'Anna Frank</Titol>
<Autor>Anne Frank</Autor>
</Llibre>
</Llibres>
```

### Obrir un document mitjançant `SAX`

`public int obrir_XML_SAX(File fitxer)`

Per obrir un document XML des de Java amb SAX s'utilitzen les classes:

* `SAXParserFactory` i `SAXParser` que pertanyen al paquet `javax.xml.parsers`
* A més a més és necessari estendre la classe `DefaultHandler` que es troba en el paquet `org.xml.sax.helpers.DefaultHandler`
* A l'exemple que ensenyarem continuació es farà servir la classe `File` per indicar la localització del fitxer `XML`. La classe `DefaultHandler` és la classe base que atén els esdeveniments retornats pel parser. Aquesta classe s'estén a les aplicacions per personalitzar el comportament del parser quan troba un element `XML`.

Com es pot entendre seguint els comentaris del codi, primerament es creen els objectes factory i parser. Aquesta part és similar a com es fa amb `DOM`. Una diferència amb `DOM` és que a `SAX` es crea una instància de la classe `HandlerLlibres`.  Aquesta classe estén `DefaultHandler` i redefineix els mètodes (callbacks) que atenen els esdeveniments.

En resum, la preparació de `SAX` requereix inicialitzar les variables, que seran usades quan s'iniciï el procés de recorregut del fitxer `XML`:

* Un objecte parser: en el codi la variable es diu parser.
* Una instància d'una classe que estengui `DefaultHandler`, que en l'exemple és `Handlerllibres`. La variable es diu `sh`.

### Recorrer un document mitjançant `SAX`

`public String recorrerSAXiMostrar()`

El mètode `parse()` llança `SAX` pel fitxer `XML` seleccionat i amb el controlador desitjat (es podrien implementar tantes extensions de `DefaultHandler` com controladors diferents es volguéssin utilitzar).

Aquest mètode rep com a paràmetre el parser inicialitzat (parser), la instància de la classe que manejarà els esdeveniments (sh) i el File amb el fitxer `XML` que recorrerà (fitxerXML).

En aquest mètode l'excepció que es captura és `SAXException` que es defineix en el paquet `org.xml.sax.SAXException`.

Per recórrer un document `XML` un cop inicialitzat el parser l'únic que es necessita és llançar el parser. Evidentment, abans cal haver definit la classe que estén `DefaultHandler`. Aquesta classe té la lògica de com actuar quan es troba algun element `XML` durant el recorregut amb `SAX` ("callbacks").

Ara veurem el codi de la classe HandlerLlibres que és el codi que gestiona com interpretar els elements d'un document `XML`.

Aquesta classe estén el mètode `startElement`, `endElement` i `characters`.

Aquests mètodes (callbacks) s'invoquen quan, durant el recorregut del document `XML`, es detecta un esdeveniment de començament d'element, final d'element o cadena de caràcters. En l'exemple, cada mètode realitza el següent:

* ***`startElement()`***: quan es detecta amb `SAX` un esdeveniment de començament d'un element, llavors `SAX` invoca aquest mètode. El que fa és comprovar de quin tipus d'element es tracta.
 * Si és `<Llibre>` llavors treu el valor del seu atribut i el concatena amb una cadena (`cadena_resultat`) que tindrà tota la sortida després de recórrer tot el document.
 * Si és `<Títol>` llavors a cadena_resultat se li concatena el text `El títol és:`.
 * Si és `<Autor>` llavors a cadena_resultat se li concatena el text `L'autor és:`.
 * Si és un altre tipus d'element no farà res.

* ***`endElement()`***: quan es detecta amb `SAX` un esdeveniment de final d'un element, llavors SAX invoca aquest mètode. El mètode comprova si és el final d'un element <Llibre>. Si és així, llavors a `cadena_resultat` se li concatena el text `\n -------------------`.

* ***`characters()`***: quan es detecta amb `SAX` un esdeveniment de detecció de cadena de text, llavors `SAX` invoca aquest mètode. El mètode el que fa és concatenar a `cadena_resultat` cadascun dels caràcters de la cadena detectada.

Si s'aplica el codi anterior al contingut al document `XML` anterior el resultat seria el següent:

~~~
Publicat l'any: 1922
El títol és: Siddharta
L'autor és: Hermann Hesse
-------------------
Publicat l'any: 1985
El títol és: El perfum
L'autor és: Patrick Süskind
-------------------
Publicat l'any: 1947
El títol és: Diari d'Anna Frank
L'autor és: Anne Frank
-------------------
~~~


### Exercicis SAX

#### Exercici 4
Donat el xml obtingut en el primer exercici de `DOM` implementar la classe `AlumnesSAX.java`, utilitzant el parser `SAX` per ensenyar la informació dels alumnes (`Nom i Edat`) per pantalla.

#### Exercici 5
Fer el mateix per al fitxer que vau crear en l'exercici 2 de `DOM` implementar la classe `EmpleatsSAX.java`.  

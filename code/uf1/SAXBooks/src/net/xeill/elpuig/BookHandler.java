package net.xeill.elpuig;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookHandler extends DefaultHandler {

  int lastElement = 0;
  String resultText = "";

  public String getResultText() {
    return resultText;
  }

  // A continuació es sobrecarreguen els esdeveniments de la classe DefaultHandler per recuperar el document XML
  // En la implementació d'aquests esdeveniments s'indica què es fa quan es trobi el començament d'un element (startElement)
  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    super.startElement(uri, localName, qName, attributes);

    if (qName.equals("Llibre")) {
      resultText += "\r" + "Publicat l'any: " + attributes.getValue(attributes.getQName(0)) + "\n";
      lastElement = 1;
    } else if (qName.equals("Titol")) {
      resultText += "El títol és: ";
      lastElement = 2;
    } else if (qName.equals("Autor")) {
      resultText += "\r" + "L'autor és: ";
      lastElement = 3;
    }
  }

  // Quan en aquest exemple detectem un final d'element <Llibre> es posa una línia a la sortida
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    super.endElement(uri, localName, qName);
    if (qName.equals("Llibre")) {
      resultText += "-------------------------";
    }
  }

  // Quan es detecta una cadena de text posterior a un dels elements <Titol> o <Autor> llavors guarda aquest
  // text en la variable corresponent
  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    super.characters(ch, start, length);
    if (lastElement == 2) {
      resultText += new String(ch, start, length);
    } else if (lastElement == 3) {
      resultText += new String(ch, start, length);
    }
  }
}

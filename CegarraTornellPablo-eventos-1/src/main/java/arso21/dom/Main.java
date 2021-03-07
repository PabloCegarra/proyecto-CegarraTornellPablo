package arso21.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) throws Exception {

		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse("xml/geolocalizacion.xml");



		NodeList nodos = documento.getElementsByTagName("entry");
		for (int i = 0; i < nodos.getLength(); i++) {
			System.out.println("Nº Entrada : " + (i + 1));
			Element entrada = (Element) nodos.item(i);
			if(entrada.getNodeType() == Node.ELEMENT_NODE) {
				Node nodo;
				if(entrada.getElementsByTagName("title") != null) {
					nodo = entrada.getElementsByTagName("title").item(0);
					System.out.println("\tNombre: " + nodo.getTextContent() );
				}
				if(entrada.getElementsByTagName("summary") != null) {
					nodo = entrada.getElementsByTagName("summary").item(0);
					System.out.println("\tResumen: " + nodo.getTextContent());
				}
				if(entrada.getElementsByTagName("wikipediaUrl") != null) {
					nodo = entrada.getElementsByTagName("wikipediaUrl").item(0);
					System.out.println("\tUrl wikipedia: " + nodo.getTextContent());
				}
			}

		}			

		System.out.println("fin.");
	}
}

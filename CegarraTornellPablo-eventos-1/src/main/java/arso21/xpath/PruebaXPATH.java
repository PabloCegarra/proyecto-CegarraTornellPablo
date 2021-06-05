package arso21.xpath;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import es.um.eventocultural.TipoGoogleBook;



public class PruebaXPATH {
		public static void main(String[] args) throws Exception{
			
			List<TipoGoogleBook> googleList = new LinkedList<TipoGoogleBook>();
			
			// 1. Obtener una factoría
			DocumentBuilderFactory factoriaDOM = DocumentBuilderFactory.newInstance();

			// 2. Pedir a la factoría la construcción del analizador
			DocumentBuilder analizador = factoriaDOM.newDocumentBuilder();

			// 3. Analizar el documento
			Document documento = analizador.parse("xml/googleBooks.xml");

			XPathFactory factoria = XPathFactory.newInstance();
			XPath xpath = factoria.newXPath();
			XPathExpression consulta;
			NodeList resultado;
			Element element;
			consulta = xpath.compile("/feed/entry");
			resultado = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
			// Recorro los resultados
			for (int i = 0; i < resultado.getLength(); i++) {
				TipoGoogleBook google = new TipoGoogleBook();
				NodeList nodeListEntry;
				
				// Recogemos el titulo del libro
				consulta = xpath.compile("/feed/entry/title[1]");	
				nodeListEntry = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
				element = (Element) nodeListEntry.item(0);
				google.setTitulo(element.getTextContent());
				System.out.println( i + "-> Titulo: " + element.getTextContent());
	
				// Recogemos la link del libro
				consulta = xpath.compile("/feed/entry/link[contains(@rel, 'http://schemas.google.com/books/2008/info')]");	
				nodeListEntry = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
				element = (Element) nodeListEntry.item(0);
				google.setLinkInfo(element.getTextContent());
				System.out.println( i + "-> Url: " + element.getAttribute("href"));
						
				// Recogemos los identificadores del libro
				consulta = xpath.compile("/feed/entry["+ (i+1) +"]/identifier");	
				nodeListEntry = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
				for (int x = 0; x < nodeListEntry.getLength(); x++) {
					element = (Element) nodeListEntry.item(x);
					System.out.println("\t" +x + " Identificador: " + element.getTextContent());
					google.getIdentifier().add(element.getTextContent());
				}
				googleList.add(google);
			}
			
			
			
		}
}




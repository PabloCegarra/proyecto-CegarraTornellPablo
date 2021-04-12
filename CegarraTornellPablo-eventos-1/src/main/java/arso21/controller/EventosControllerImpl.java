package arso21.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import arso21.sax.ListadoEventos;
import arso21.sax.ManejadorEventos;

public class EventosControllerImpl implements EventosController{

	public final static String URL_GEONAMES = "http://api.geonames.org/findNearbyWikipedia?";
	public final static String URI_GOOGLE_BOOKS = "https://books.google.com/books/feeds/volumes?q=";
	public final static String URI_EVENTO_INFO = "https://datos.madrid.es/egob/catalogo/tipo/evento/";
	public final static String URI_EVENTOS_MADRID = "https://datos.madrid.es/egob/catalogo/206974-0-agenda-eventos-culturales-100.xml";

	public String llamarServicio(String url) {
		String result = "";
		try {
			URL urlFind = new URL(url);
			URLConnection connection = urlFind.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public ListadoEventos findEventos() throws Exception {
		String result = "";
		result = llamarServicio(URI_EVENTOS_MADRID);

		ListadoEventos listadoEventos = new ListadoEventos();
		
		if (!result.isEmpty()) {
			listadoEventos = procesarListadoEventos(result);

		} else
			result = "error en la llamada al servicio de busqueda de autor";
		return listadoEventos;
	}
	
	public ListadoEventos procesarListadoEventos(String xmlStr) throws Exception {
		ListadoEventos listadoEventos = new ListadoEventos();
		SAXParserFactory factoria = SAXParserFactory.newInstance();
		SAXParser analizador = factoria.newSAXParser(); 

		try {
			ManejadorEventos manejador = new ManejadorEventos();
			analizador.parse(new InputSource(new StringReader(xmlStr)), manejador);
			listadoEventos =  manejador.getListadoEventos();
			
			
		} 
		catch (IOException e) {
			System.out.println("El documento no ha podido ser leído");
		}
		catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}
		return listadoEventos;
				
	}
	
	
}

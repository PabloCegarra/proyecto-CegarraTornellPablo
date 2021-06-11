package eventos.sax;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class PruebaSAX {

	
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		
		
		// 1. Obtener una factoría
		SAXParserFactory factoria = SAXParserFactory.newInstance();
	
		// 2. Pedir a la factoría la construcción del analizador
		SAXParser analizador = factoria.newSAXParser(); 
		
		// 3. Analizar el documento

		try {
			ManejadorEventos manejador = new ManejadorEventos();
			analizador.parse("xml/agenda.xml", manejador);
			ListadoEventos eventos =  manejador.getListadoEventos();
			int contador = 0;
			for(EventoResumen evento : eventos.getEventos()) {
				contador++;
				System.out.println("Evento Nº: " + contador);
				System.out.println("\t" + evento.getId());
				System.out.println("\t" + evento.getTitulo());
				System.out.println("\t" + evento.getUrl());
				System.out.println("\t" + evento.getFechaInicio());
				System.out.println("\t" + evento.getDescripcion());
				System.out.println("\t" + evento.getTipo());


			}
			System.out.println("Numero de eventos: " + eventos.getEventos().size());
			// TODO: aquí le pedimos al manejador el resultado
			
		} 
		catch (IOException e) {
			System.out.println("El documento no ha podido ser leído");
		}
		catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}

		System.out.println("fin.");		
	}
}

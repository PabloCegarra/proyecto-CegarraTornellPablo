package arso21.sax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorEventos extends DefaultHandler {

	// Informacion
	private LinkedList<EventoResumen> eventos;
	private ListadoEventos listadoEventos;
	private Integer numEventos = 0;
	private EventoResumen eventoResumen;
	private String tipoAtributo;

	// Variables control
	private boolean dentroAtributo = false;
	private boolean dentroEvento = false;

	// Soporte
	private LinkedList<String> pila;

	@Override
	public void startDocument() throws SAXException {

		this.eventos = new LinkedList<EventoResumen>();
		this.listadoEventos = new ListadoEventos();
		this.pila = new LinkedList<String>();
	}

	@Override
	public void endDocument() throws SAXException {
		this.listadoEventos.setEventos(this.eventos);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		pila.push(qName);

		if (qName.equals("contenido")) {
			dentroEvento = true;
			this.eventoResumen = new EventoResumen();

		} else if (qName.equals("atributo")) {
			String tipoAtributo = attributes.getValue("nombre");
			switch (tipoAtributo) {
			case "TITULO":
				this.tipoAtributo = tipoAtributo;
				dentroAtributo = true;
				break;
			case "ID-EVENTO":
				this.tipoAtributo = tipoAtributo;
				dentroAtributo = true;
				break;
			case "FECHA-EVENTO":
				this.tipoAtributo = tipoAtributo;
				dentroAtributo = true;
				break;
			case "DESCRIPCION":
				this.tipoAtributo = tipoAtributo;
				dentroAtributo = true;
				break;
			case "TIPO":
				this.tipoAtributo = tipoAtributo;
				dentroAtributo = true;
				break;

			default:
				this.tipoAtributo = "NINGUN ATRIBUTO QUE NECESITEMOS";
				dentroAtributo = false;
				break;
			}
		}

		numEventos++;

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		

		if (dentroAtributo) {
			String texto = new String(ch, start, length);
			switch (this.tipoAtributo) {
			case "TITULO":
				this.eventoResumen.setTitulo(texto);
				dentroAtributo = false;
				break;
			case "ID-EVENTO":
				this.eventoResumen.setId(texto);
				dentroAtributo = false;
				break;
			case "FECHA-EVENTO":
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");			
				Date fecha = null;
				
				try {
					fecha = dateFormat.parse(texto);
				} catch (ParseException e) {
					throw new SAXException("Fecha incorrecta. El documento no ha sido validado");
				}
				this.eventoResumen.setFechaInicio(fecha);
				dentroAtributo = false;
				break;
			case "DESCRIPCION":
				this.eventoResumen.setDescripcion(texto);
				dentroAtributo = false;
				break;
			case "TIPO":
				this.eventoResumen.setTipo(texto);
				dentroAtributo = false;
				break;

			default:
				this.tipoAtributo = "NINGUN ATRIBUTO QUE NECESITEMOS";
				dentroAtributo = false;
				break;
			}
		}

		/*
		 * En este evento se recupera el texto que envuelve un elemento. Para conocer de
		 * que elemento se trata, debemos consultar los atributos de estado o la cima de
		 * la pila:
		 * 
		 * String elemento = pila.peek()
		 */

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equals("contenido")) {
			this.eventos.add(eventoResumen);
			dentroEvento = false;

		} else if (qName.equals("atributo")) {
			dentroAtributo = false;
		}
		pila.pop();
	}

	public ListadoEventos getListadoEventos() {
		return listadoEventos;
	}

	public Integer getNumEventos() {
		return numEventos;
	}

}

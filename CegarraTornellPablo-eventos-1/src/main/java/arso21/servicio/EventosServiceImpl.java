package arso21.servicio;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import arso21.repositorio.EntidadNoEncontrada;
import arso21.repositorio.FactoriaRepositorioEventoCultural;
import arso21.repositorio.RepositorioEventoCultural;
import arso21.repositorio.RepositorioException;
import arso21.sax.EventoResumen;
import arso21.sax.ListadoEventos;
import arso21.sax.ManejadorEventos;
import es.um.eventocultural.EventoCultural;
import es.um.eventocultural.TipoActuaciones;
import es.um.eventocultural.TipoGoogleBook;

public class EventosServiceImpl implements IEventosService {

	public final static String URL_GEONAMES = "http://api.geonames.org/findNearbyWikipedia?";
	public final static String URI_GOOGLE_BOOKS = "https://books.google.com/books/feeds/volumes?q=";
	public final static String URI_EVENTO_INFO = "https://datos.madrid.es/egob/catalogo/tipo/evento/";
	public final static String URI_EVENTOS_MADRID = "https://datos.madrid.es/egob/catalogo/206974-0-agenda-eventos-culturales-100.xml";

	private RepositorioEventoCultural repositorio = FactoriaRepositorioEventoCultural.getRepositorio();

	private static EventosServiceImpl instancia;


	public static EventosServiceImpl getInstancia() {

		if (instancia == null)
			instancia = new EventosServiceImpl();

		return instancia;
	}

	private String llamarServicio(String url) {
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
	public ListadoEventos findListadoEventos() throws Exception {
		String result = "a";
		//result = llamarServicio(URI_EVENTOS_MADRID);

		ListadoEventos listadoEventos = new ListadoEventos();

		if (!result.isEmpty()) {
			listadoEventos = procesarListadoEventos(result);

		} else
			result = "error en la llamada al servicio de busqueda de autor";
		return listadoEventos;
	}

	private ListadoEventos procesarListadoEventos(String xmlStr) throws Exception {
		ListadoEventos listadoEventos = new ListadoEventos();
		SAXParserFactory factoria = SAXParserFactory.newInstance();
		SAXParser analizador = factoria.newSAXParser();

		try {
			ManejadorEventos manejador = new ManejadorEventos();
			//analizador.parse(new InputSource(new StringReader(xmlStr)), manejador);
			analizador.parse("xml/agenda.xml", manejador);
			listadoEventos = manejador.getListadoEventos();
			int contador = 0;
			for(EventoResumen evento : listadoEventos.getEventos()) {
				contador++;
				System.out.println("Evento Nº: " + contador);
				System.out.println("\t" + evento.getId());
				System.out.println("\t" + evento.getTitulo());
				System.out.println("\t" + evento.getUrl());
				System.out.println("\t" + evento.getFechaInicio());
				System.out.println("\t" + evento.getDescripcion());
				System.out.println("\t" + evento.getTipo());


			}
			System.out.println("Numero de eventos: " + listadoEventos.getEventos().size());

		} catch (IOException e) {
			System.out.println("El documento no ha podido ser leído");
		} catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}
		return listadoEventos;

	}

	@Override
	public EventoCultural findInfoEvento(String url) throws Exception {
		String result = "";
		EventoCultural evento;
		result = llamarServicio(url);
		InputStream inputStream = new ByteArrayInputStream(result.getBytes(Charset.forName("UTF-8")));
		evento = procesarJsonEvento(inputStream);

		evento = procesarGeoname(evento);
		if ((evento.getTipo().equals("CursosTalleres")) && (getListaTitulosLibros(evento).size() > 0))
			evento = procesarGoogleBooks(evento);
		return evento;
	}

	private List<String> getListaTitulosLibros(EventoCultural evento) {
		List<String> listaTitulos = new LinkedList<String>();
		String titulo = evento.getNombre();
		Pattern p = Pattern.compile("(?:^|\\s)'([^']*?)'(?:$|\\s)", Pattern.MULTILINE);

		Matcher m = p.matcher(titulo);
		if (m.find()) {
			listaTitulos.add(m.group());
			while (m.find())
				listaTitulos.add(m.group());
		}
		return listaTitulos;
	}

	private EventoCultural procesarGoogleBooks(EventoCultural eventoCultural) throws Exception {

		EventoCultural evento = eventoCultural;
		List<String> listaTitulos = getListaTitulosLibros(eventoCultural);

		for (String titulo : listaTitulos) {
			String urlGoogle = URI_GOOGLE_BOOKS;
			String encodeStr = URLEncoder.encode(titulo, StandardCharsets.UTF_8.name());
			urlGoogle+= encodeStr + "&start-index=1&max-results=5";
			
			String result = llamarServicio(urlGoogle);
			
			DocumentBuilderFactory factoriaDOM = DocumentBuilderFactory.newInstance();
			DocumentBuilder analizador = factoriaDOM.newDocumentBuilder();
			Document documento = analizador.parse(new InputSource(new StringReader(result)));
			XPathFactory factoria = XPathFactory.newInstance();
			XPath xpath = factoria.newXPath();
			XPathExpression consulta;
			NodeList resultado;
			Element element;
			
			consulta = xpath.compile("/feed/entry");
			resultado = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
			// Recorro los resultados
			TipoGoogleBook google = new TipoGoogleBook();
			for (int i = 0; i < resultado.getLength(); i++) {
				google = new TipoGoogleBook();
				NodeList nodeListEntry;

				// Recogemos el titulo del libro
				consulta = xpath.compile("/feed/entry/title[1]");
				nodeListEntry = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
				element = (Element) nodeListEntry.item(0);
				google.setTitulo(element.getTextContent());
				// Recogemos la link del libro
				consulta = xpath
						.compile("/feed/entry/link[contains(@rel, 'http://schemas.google.com/books/2008/info')]");
				nodeListEntry = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
				element = (Element) nodeListEntry.item(0);
				google.setLinkInfo(element.getAttribute("href"));

				// Recogemos los identificadores del libro
				consulta = xpath.compile("/feed/entry/identifier");
				nodeListEntry = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
				for (int x = 0; x < nodeListEntry.getLength(); x++) {
					element = (Element) nodeListEntry.item(x);
					google.getIdentifier().add(element.getTextContent());
				}
			}
			evento.getGoogleBooks().add(google);
		}
		return evento;
	}

	private EventoCultural procesarGeoname(EventoCultural eventoCultural) throws Exception {

		EventoCultural evento = eventoCultural;
		String urlGeoname = "";
		if (evento.getCoordenadaLatitud() != null && evento.getCoordenadaLongitud() != null) {
			urlGeoname = URL_GEONAMES + "lat=" + evento.getCoordenadaLatitud() + "&lng="
					+ evento.getCoordenadaLongitud() + "&lang=es&username=arso";
			String resultadoXML = llamarServicio(urlGeoname);

			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder analizador = factoria.newDocumentBuilder();
			Document documento = analizador.parse(new InputSource(new StringReader(resultadoXML)));

			NodeList nodos = documento.getElementsByTagName("entry");
			for (int i = 0; i < nodos.getLength(); i++) {
				Element entrada = (Element) nodos.item(i);
				if (entrada.getNodeType() == Node.ELEMENT_NODE) {
					Node nodo;
					if (entrada.getElementsByTagName("wikipediaUrl") != null) {
						nodo = entrada.getElementsByTagName("wikipediaUrl").item(0);
						evento.setUrlWikipedia(nodo.getTextContent());
					}
				}

			}
		}

		return evento;
	}

	private EventoCultural procesarJsonEvento(InputStream inputStream) {
		EventoCultural eventoObject = new EventoCultural();

		JsonReader jsonReader = Json.createReader(inputStream);
		JsonObject eventoJson = jsonReader.readObject();
		JsonArray contenidoEvento = eventoJson.getJsonArray("@graph");
		// montar objeto evento
		for (JsonObject campo : contenidoEvento.getValuesAs(JsonObject.class)) {
			eventoObject.setId(campo.getString("id"));
			eventoObject.setNombre(campo.getString("title"));
			eventoObject.setFechaInicio(campo.getString("dtstart"));
			eventoObject.setFechaFin(campo.getString("dtend"));
			eventoObject.setUrl(campo.getString("link"));
			if (campo.containsKey("event-location"))
				eventoObject.setLocalizacion(campo.getString("event-location"));
			if (campo.containsKey("description"))
				eventoObject.setResumen(campo.getString("description"));

			if (campo.containsKey("recurrence")) {
				TipoActuaciones actuacionesObject = new TipoActuaciones();
				JsonObject actuaciones = campo.getJsonObject("recurrence");
				actuacionesObject.setDias(actuaciones.getString("days"));
				actuacionesObject.setFrecuencia(actuaciones.getString("frequency"));
				actuacionesObject.setIntervalo(BigInteger.valueOf(actuaciones.getInt("interval")));
				eventoObject.setActuaciones(actuacionesObject);
			}
			if (campo.containsKey("@type")) {
				String urlTipo = campo.getString("@type");
				String[] parts = urlTipo.split("/");
				String tipo = parts[parts.length - 1];
				eventoObject.setTipo(tipo);
			}

			if (campo.containsKey("location")) {
				JsonObject coordenadas = campo.getJsonObject("location");
				eventoObject.setCoordenadaLatitud(coordenadas.getJsonNumber("latitude").doubleValue());
				eventoObject.setCoordenadaLongitud(coordenadas.getJsonNumber("longitude").doubleValue());
			}
		}
		return eventoObject;
	}

	@Override
	public String createEvento(EventoCultural evento) throws RepositorioException {
		return repositorio.add(evento);
	}

	@Override
	public void update(EventoCultural evento) throws RepositorioException, EntidadNoEncontrada {
		repositorio.update(evento);
	}

	@Override
	public EventoCultural getEvento(String id) throws RepositorioException, EntidadNoEncontrada {
		return repositorio.getById(id);
	}

	@Override
	public void removeEvento(String id) throws RepositorioException, EntidadNoEncontrada {

		EventoCultural evento = repositorio.getById(id);

		repositorio.delete(evento);
	}
	
	public void borrarRepositorio() {
		repositorio.borrarBBDD();
	}

}

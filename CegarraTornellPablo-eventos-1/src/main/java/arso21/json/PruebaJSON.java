package arso21.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;

import arso21.repositorio.RepositorioEventoCulturalXML;
import es.um.eventocultural.EventoCultural;
import es.um.eventocultural.TipoActuaciones;
import es.um.eventocultural.TipoGoogleBook;

public class PruebaJSON {

	public final static String RUTA_GEONAMES = "http://api.geonames.org/findNearbyWikipedia?";

	public static void main(String[] args) throws Exception {

		String urlGeoname = "";

		EventoCultural eventoObject = new EventoCultural();

		InputStreamReader fuente = new FileReader("jsonEventosRepositorio/11322033.json");
		JsonReader jsonReader = Json.createReader(fuente);
		JsonObject eventoJson = jsonReader.readObject();
		JsonArray contenidoEvento = eventoJson.getJsonArray("@graph");
		// montar objeto evento
		for (JsonObject campo : contenidoEvento.getValuesAs(JsonObject.class)) {
			eventoObject.setId(campo.getString("id"));
			eventoObject.setNombre(campo.getString("title"));
			eventoObject.setFechaInicio(campo.getString("dtstart"));
			eventoObject.setFechaFin(campo.getString("dtend"));
			eventoObject.setUrl(campo.getString("link"));
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
				String urlTipo= campo.getString("@type");
				String[] parts = urlTipo.split("/");
				String tipo = parts[parts.length-1];
				eventoObject.setTipo(tipo);
			}

			if (campo.containsKey("location")) {
				JsonObject coordenadas = campo.getJsonObject("location");
				eventoObject.setCoordenadaLatitud(coordenadas.getJsonNumber("latitude").doubleValue());
				eventoObject.setCoordenadaLongitud(coordenadas.getJsonNumber("longitude").doubleValue());
			}
		}
		TipoGoogleBook google = new TipoGoogleBook();
		google.setLinkInfo("link prueba");
		google.setTitulo("titulo prueba");
		google.getIdentifier().add("1");
		google.getIdentifier().add("2");
		eventoObject.getGoogleBooks().add(google);
		
		RepositorioEventoCulturalXML repositorio = new RepositorioEventoCulturalXML();
		repositorio.add(eventoObject);

		if (eventoObject.getCoordenadaLatitud() != null && eventoObject.getCoordenadaLongitud() != null) {
			urlGeoname = RUTA_GEONAMES + "lat=" + eventoObject.getCoordenadaLatitud() + "&lng="
					+ eventoObject.getCoordenadaLongitud() + "&lang=es&username=arso";
		}
		System.out.println("URL GEONAMES: " + urlGeoname); 
		System.out.println("Fichero generado en xmlEventosRepositorio: " + eventoObject.getId() + ".xml");

	}

}

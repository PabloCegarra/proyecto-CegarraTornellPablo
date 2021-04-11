//package arso21.repositorio;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.InputStreamReader;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//
//import arso21.repositorio.utils.Utils;
//import es.um.eventocultural.EventoCultural;
//import es.um.eventocultural.TipoActuaciones;
//
//public class RepositorioEventoCulturalJSON implements RepositorioEventoCultural{
//	
//	public final static String DIRECTORIO_EVENTO = "jsonEventosRepositorio/";
//
//	static {
//
//		File directorio = new File(DIRECTORIO_EVENTO);
//
//		if (!directorio.exists())
//			directorio.mkdir();
//	}
//	
//	/*** Métodos de apoyo ***/
//
//	protected String getDocumento(String id) {
//
//		return DIRECTORIO_EVENTO + id + ".json";
//	}
//
//	protected boolean checkDocumento(String id) {
//
//		final String documento = getDocumento(id);
//
//		File fichero = new File(documento);
//
//		return fichero.exists();
//	}
//	
//	protected void save(EventoCultural evento) throws RepositorioException {
//
//		final String documento = getDocumento(evento.getId());
//
//		final File fichero = new File(documento);
//
//		try {
//
//			
//			
//
//
//		} catch (Exception e) {
//
//			throw new RepositorioException("Error al guardar el evento cultural con id: " + evento.getId(), e);
//		}
//	}
//	
//	@Override
//	public String add(EventoCultural evento) throws RepositorioException {
//
//		String id = Utils.createId();
//
//		evento.setId(id);
//		save(evento);
//
//		return id;
//	}
//	
//	protected EventoCultural load(String id) throws RepositorioException, EntidadNoEncontrada {
//
//		if (!checkDocumento(id))
//			throw new EntidadNoEncontrada("El evento no existe, id: " + id);
//
//		final String documento = getDocumento(id);
//
//		try {
//
//			EventoCultural eventoObject = new EventoCultural();
//
//			InputStreamReader fuente = new FileReader(documento);
//			JsonReader jsonReader = Json.createReader(fuente);
//			JsonObject eventoJson = jsonReader.readObject();
//			//montar objeto evento 
//			eventoObject.setId(eventoJson.getString("id"));
//			eventoObject.setNombre(eventoJson.getString("title"));
//			eventoObject.setFechaInicio(eventoJson.getString("dtstart"));
//			eventoObject.setFechaFin(eventoJson.getString("dtend"));
////			if(eventoJson.containsKey("descripción"))
////				eventoObject.setResumen(eventoJson.gettring("descripción"));
//			eventoObject.setUrl(eventoJson.getString("url"));
//			if(eventoJson.containsKey("urlWikipedia"))
//				eventoObject.setUrlWikipedia(eventoJson.getString("urlWikipedia"));
//			eventoObject.setLocalizacion(eventoJson.getString("localizacion"));
//			eventoObject.setCoordenadaLatitud(Float.parseFloat(eventoJson.getString("coordenadaLatitud")));
//			eventoObject.setCoordenadaLongitud(Float.parseFloat(eventoJson.getString("coordenadaLongitud")));
//
//			
//			
//
//		} catch (Exception e) {
//			throw new RepositorioException("Error al cargar el evento con id: " + id, e);
//		}
//	}
//	
//	@Override
//	public EventoCultural getById(String id) throws RepositorioException, EntidadNoEncontrada {
//
//		return load(id);
//	}
//	
//}

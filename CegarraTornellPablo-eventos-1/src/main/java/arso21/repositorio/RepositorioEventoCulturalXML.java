package arso21.repositorio;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import arso21.mapeo.RootElement;
import arso21.repositorio.utils.Utils;
import es.um.eventocultural.EventoCultural;



public class RepositorioEventoCulturalXML implements RepositorioEventoCultural {

	public final static String DIRECTORIO_EVENTO = "xmlEventosRepositorio/";
	
	private static RootElement mapObject;
	
	private static JAXBContext contexto;

	static {

		File directorio = new File(DIRECTORIO_EVENTO);
		
		
		try {
			contexto = JAXBContext.newInstance(RootElement.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			mapObject = (RootElement) unmarshaller.unmarshal(new File("xml/mapaIndice.xml"));

			if (!directorio.exists())
				directorio.mkdir();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	/*** Métodos de apoyo 
	 * @throws JAXBException ***/
	
	protected void actualizarIndice(Map<String, String> mapa) throws JAXBException {
		RepositorioEventoCulturalXML.mapObject.setMapProperty(mapa);
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
		
		marshaller.marshal(RepositorioEventoCulturalXML.mapObject, new File("xml/mapaIndice.xml"));
		
	}

	protected String getDocumento(String id) {

		return DIRECTORIO_EVENTO + id + ".xml";
	}

	protected boolean checkDocumento(String id) {

		final String documento = getDocumento(id);

		File fichero = new File(documento);

		return fichero.exists();
	}

	protected void save(EventoCultural evento) throws RepositorioException {

		final String documento = getDocumento(evento.getId());

		final File fichero = new File(documento);

		try {

			JAXBContext contexto = JAXBContext.newInstance(EventoCultural.class);
			Marshaller marshaller = contexto.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "EventoCultural.xsd");
			marshaller.marshal(evento, fichero);

		} catch (Exception e) {

			throw new RepositorioException("Error al guardar el evento cultural con id: " + evento.getId(), e);
		}
	}

	protected EventoCultural load(String id) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocumento(id))
			throw new EntidadNoEncontrada("El evento no existe, id: " + id);

		final String documento = getDocumento(id);

		try {

			JAXBContext contexto = JAXBContext.newInstance(EventoCultural.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();

			return (EventoCultural) unmarshaller.unmarshal(new File(documento));

		} catch (Exception e) {
			throw new RepositorioException("Error al cargar el evento con id: " + id, e);
		}
	}

	/*** Fin métodos de apoyo ***/

	@Override
	public String add(EventoCultural evento) throws RepositorioException {

		String id = Utils.createId();
		
		
		//Actualizamos indice de eventos

		Map<String, String> map = mapObject.getMapProperty();
		
		if(map.get(evento.getUrl())!= null){
			return map.get(evento.getUrl());
		}else{
			try {
				evento.setId(id);
				save(evento);
				map.put(evento.getUrl(), id);
				actualizarIndice(map);
				return id;
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return id;
		}
	}

	@Override
	public void update(EventoCultural evento) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocumento(evento.getId()))
			throw new EntidadNoEncontrada("El evento no existe, id: " + evento.getId());

		save(evento);
		
	}

	@Override
	public void delete(EventoCultural evento) throws EntidadNoEncontrada {

		if (!checkDocumento(evento.getId()))
			throw new EntidadNoEncontrada("El evento no existe, id: " + evento.getId());

		//Actualizamos indice de eventos
		Map<String, String> map = mapObject.getMapProperty();
		if(map.get(evento.getUrl())!= null){
			map.remove(evento.getUrl());
			try {
				actualizarIndice(map);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		
		final String documento = getDocumento(evento.getId());

		File fichero = new File(documento);

		fichero.delete();
	}

	@Override
	public EventoCultural getById(String id) throws RepositorioException, EntidadNoEncontrada {

		return load(id);
	}

	@Override
	public List<String> getIds() {

		LinkedList<String> resultado = new LinkedList<>();

		File directorio = new File(DIRECTORIO_EVENTO);

		File[] eventos = directorio.listFiles(f -> f.isFile() && f.getName().endsWith(".xml"));

		for (File file : eventos) {

			String id = file.getName().substring(0, file.getName().length() - 4);

			resultado.add(id);
		}

		return resultado;
	}

	@Override
	public List<EventoCultural> getAll() throws RepositorioException {

		LinkedList<EventoCultural> resultado = new LinkedList<EventoCultural>();

		for (String id : getIds()) {
			
			try {
				resultado.add(load(id));
			} catch (EntidadNoEncontrada e) {

				throw new RepositorioException("Error al cargar el evento: " + id, e);
			}
		}
		
		return resultado;
	}
	
	public void borrarBBDD() {
	    File[] archivos = new File(DIRECTORIO_EVENTO).listFiles(((new FileFilter() {
	        public boolean accept(File archivo) {
	            if (archivo.isFile())
	                return archivo.getName().endsWith(".xml");
	            return false;
	        }
	    })));
	    for (File archivo : archivos)
	        archivo.delete();
	}
	
	@Override
	public EventoCultural getByURL(String url) throws RepositorioException, EntidadNoEncontrada {

		Map<String, String> map = mapObject.getMapProperty();
		String id = map.get(url);
		
		return load(id);
	}


}

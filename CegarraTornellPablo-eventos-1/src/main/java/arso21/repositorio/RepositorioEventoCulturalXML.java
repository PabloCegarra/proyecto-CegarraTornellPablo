package arso21.repositorio;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import arso21.repositorio.utils.Utils;
import arso21.sax.EventoResumen;
import es.um.eventocultural.EventoCultural;



public class RepositorioEventoCulturalXML implements RepositorioEventoCultural {

	public final static String DIRECTORIO_EVENTO = "xmlEventosRepositorio/";

	static {

		File directorio = new File(DIRECTORIO_EVENTO);

		if (!directorio.exists())
			directorio.mkdir();
	}

	/*** Métodos de apoyo ***/

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

		evento.setId(id);
		save(evento);

		return id;
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

}

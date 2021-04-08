package arso21.repositorio;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import arso21.repositorio.utils.Utils;
import arso21.sax.EventoResumen;



public class RepositorioEventoCulturalXML implements RepositorioEventoCultural {

	public final static String DIRECTORIO_EVENTO = "xmlEventosCulturales/";

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

	protected void save(EventoResumen actividad) throws RepositorioException {

		final String documento = getDocumento(actividad.getId());

		final File fichero = new File(documento);

		try {

			JAXBContext contexto = JAXBContext.newInstance("org.example.eventocultural");
			Marshaller marshaller = contexto.createMarshaller();

			marshaller.setProperty("jaxb.formatted.output", true);

			marshaller.marshal(actividad, fichero);

		} catch (Exception e) {

			throw new RepositorioException("Error al guardar el evento cultural con id: " + actividad.getId(), e);
		}
	}

	protected EventoResumen load(String id) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocumento(id))
			throw new EntidadNoEncontrada("El evento no existe, id: " + id);

		final String documento = getDocumento(id);

		try {

			JAXBContext contexto = JAXBContext.newInstance("org.example.eventocultural");
			Unmarshaller unmarshaller = contexto.createUnmarshaller();

			return (EventoResumen) unmarshaller.unmarshal(new File(documento));

		} catch (Exception e) {
			throw new RepositorioException("Error al cargar el evento con id: " + id, e);
		}
	}

	/*** Fin métodos de apoyo ***/

	@Override
	public String add(EventoResumen evento) throws RepositorioException {

		String id = Utils.createId();

		evento.setId(id);
		save(evento);

		return id;
	}

	@Override
	public void update(EventoResumen evento) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocumento(evento.getId()))
			throw new EntidadNoEncontrada("El evento no existe, id: " + evento.getId());

		save(evento);
		
	}

	@Override
	public void delete(EventoResumen evento) throws EntidadNoEncontrada {

		if (!checkDocumento(evento.getId()))
			throw new EntidadNoEncontrada("El evento no existe, id: " + evento.getId());

		final String documento = getDocumento(evento.getId());

		File fichero = new File(documento);

		fichero.delete();
	}

	@Override
	public EventoResumen getById(String id) throws RepositorioException, EntidadNoEncontrada {

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
	public List<EventoResumen> getAll() throws RepositorioException {

		LinkedList<EventoResumen> resultado = new LinkedList<EventoResumen>();

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

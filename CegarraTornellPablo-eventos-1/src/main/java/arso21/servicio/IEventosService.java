package arso21.servicio;

import arso21.repositorio.EntidadNoEncontrada;
import arso21.repositorio.RepositorioException;
import arso21.sax.ListadoEventos;
import es.um.eventocultural.EventoCultural;

public interface IEventosService {
	
	ListadoEventos findListadoEventos() throws Exception;
	
	EventoCultural findInfoEvento(String url) throws Exception;
	
	String createEvento(EventoCultural evento) throws RepositorioException;
	
	void update(EventoCultural evento) throws RepositorioException, EntidadNoEncontrada;
	
	EventoCultural getEvento(String id) throws RepositorioException, EntidadNoEncontrada;
	
	void removeEvento(String id) throws RepositorioException, EntidadNoEncontrada;

}

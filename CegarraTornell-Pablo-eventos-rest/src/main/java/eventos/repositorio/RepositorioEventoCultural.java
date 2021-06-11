package eventos.repositorio;


import es.um.eventocultural.EventoCultural;

public interface RepositorioEventoCultural extends Repositorio<EventoCultural, String> {

	// Se pueden incluir métodos adicionales
	public EventoCultural getByURL(String url) throws RepositorioException, EntidadNoEncontrada;
	
	
}

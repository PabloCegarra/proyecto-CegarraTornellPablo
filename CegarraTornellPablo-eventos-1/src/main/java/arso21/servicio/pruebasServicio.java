package arso21.servicio;

import arso21.repositorio.FactoriaRepositorioEventoCultural;
import arso21.repositorio.RepositorioEventoCultural;
import es.um.eventocultural.EventoCultural;

public class pruebasServicio {
	public static void main(String[] args) throws Exception {

		RepositorioEventoCultural repositorio = FactoriaRepositorioEventoCultural.getRepositorio();
		
		IEventosService servicioEventos = EventosServiceImpl.getInstancia();
		// Probar findListadoEventos
		servicioEventos.findListadoEventos();
		EventoCultural evento = servicioEventos
				.findInfoEvento("https://datos.madrid.es/egob/catalogo/tipo/evento/11340884.json");
		String idEvento = repositorio.add(evento);
		System.out.println("Id Evento creado en repositorio xmlEventosRepositorio: " + idEvento);
		EventoCultural evento2 = repositorio.getById(idEvento);
		String idEvento2 = repositorio.add(evento2);
		System.out.println("Id Evento creado en repositorio xmlEventosRepositorio: " + idEvento2);
		repositorio.delete(evento2);
		System.out.println("Id Evento borrado en repositorio xmlEventosRepositorio: " + idEvento2);
		
		
		
		
		EventoCultural evento3 = servicioEventos
				.findInfoEvento("https://datos.madrid.es/egob/catalogo/tipo/evento/11322033.json");
		idEvento = repositorio.add(evento3);
		System.out.println("Id Evento creado en repositorio xmlEventosRepositorio: " + idEvento);
		EventoCultural evento4 = repositorio.getById(idEvento);
		//servicioEventos.borrarRepositorio();
		//System.out.println("Limpiado el repositorio xmlEventosRepositorio: ");

	}
}

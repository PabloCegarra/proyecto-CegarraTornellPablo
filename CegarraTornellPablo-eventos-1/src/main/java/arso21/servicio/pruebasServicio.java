package arso21.servicio;

import es.um.eventocultural.EventoCultural;

public class pruebasServicio {
	public static void main(String[] args) throws Exception {

		IEventosService servicioEventos = EventosServiceImpl.getInstancia();
		// Probar findListadoEventos
		servicioEventos.findListadoEventos();
		EventoCultural evento = servicioEventos
				.findInfoEvento("https://datos.madrid.es/egob/catalogo/tipo/evento/11340884.json");
		String idEvento = servicioEventos.createEvento(evento);
		System.out.println("Id Evento creado en repositorio xmlEventosRepositorio: " + idEvento);
		EventoCultural evento2 = servicioEventos.getEvento(idEvento);
		String idEvento2 = servicioEventos.createEvento(evento2);
		System.out.println("Id Evento creado en repositorio xmlEventosRepositorio: " + idEvento2);
		servicioEventos.removeEvento(idEvento2);
		System.out.println("Id Evento borrado en repositorio xmlEventosRepositorio: " + idEvento2);
		
		
		
		
		EventoCultural evento3 = servicioEventos
				.findInfoEvento("https://datos.madrid.es/egob/catalogo/tipo/evento/11322033.json");
		idEvento = servicioEventos.createEvento(evento3);
		System.out.println("Id Evento creado en repositorio xmlEventosRepositorio: " + idEvento);
		EventoCultural evento4 = servicioEventos.getEvento(idEvento);
		//servicioEventos.borrarRepositorio();
		//System.out.println("Limpiado el repositorio xmlEventosRepositorio: ");

	}
}

package arso21.repositorio;

import javax.xml.bind.JAXBContext;

import es.um.eventocultural.EventoCultural;

public class TestRepositorio {

	
	public static void main(String[] args) throws Exception {

		RepositorioEventoCultural repositorio = FactoriaRepositorioEventoCultural.getRepositorio();
		
		EventoCultural evento = new EventoCultural();
		
		evento.setFechaFin("dsdef");
		evento.setFechaInicio("dsdef");
		evento.setId("dsdef");
		evento.setNombre("dsdef");
		evento.setUrl("dsdef");

		String id = repositorio.add(evento);

		System.out.println("Id. del evento: " + id);
		
		System.out.println("Fin.");

	}
}

package arso21.repositorio;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.um.eventocultural.EventoCultural;

public class TestRepositorio {

	
	public static void main(String[] args) throws Exception {

		RepositorioEventoCultural repositorio = FactoriaRepositorioEventoCultural.getRepositorio();
		
		EventoCultural evento = new EventoCultural();
		
		evento.setFechaFin("prueba");
		evento.setFechaInicio("prueba");
		evento.setId("prueba");
		evento.setNombre("De 3 a 5 años: 'Hansel y Gretel' y 'Caperucita roja' y  'Hola' y 'eee'");
		evento.setUrl("prueba");
		


		String id = repositorio.add(evento);
		System.out.println("Id. del evento: " + id);
		System.out.println("Mostrando evento de prueba\n");
		EventoCultural evento2 = repositorio.getById("pruebaEvento");
		System.out.println(evento2.toString());
		repositorio.delete(evento);
		System.out.println("Borrando evento: " + id);
		
		System.out.println("Id. del evento: " + id);
		
		System.out.println("Fin.");

	}
}

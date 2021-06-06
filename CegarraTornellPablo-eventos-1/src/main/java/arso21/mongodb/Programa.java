package arso21.mongodb;

import java.util.HashSet;
import java.util.Set;

public class Programa {

	public static void main(String[] args) throws Exception {

		RepositorioEnlacesFavoritos repositorio = FactoriaRepositorioEnlacesFavoritos.getRepositorio();

		// Creamos un documento vacio
		String idDocumento1 = repositorio.createEmpty();

		// Añadimos una Url favorita
		Favorito fav1 = new Favorito();
		fav1.setEtiquetas(new HashSet<String>());
		fav1.setUrl("UrlFav1");
		Set<String> et1 = new HashSet<String>();
		et1.add("et1");
		et1.add("et2");
		et1.add("et3");
		fav1.setEtiquetas(et1);

		repositorio.addUrlFavorita(idDocumento1, fav1);

		// Añadimos otra Url favorita
		Favorito fav2 = new Favorito();
		fav2.setUrl("UrlFav2");
		Set<String> et2 = new HashSet<String>();
		et2.add("et4");
		et2.add("et5");
		et2.add("et6");
		fav2.setEtiquetas(et2);
		
		repositorio.addUrlFavorita(idDocumento1, fav2);
		
		System.out.println("getAllEtiquetas: ");
		repositorio.getAllEtiquetas(idDocumento1).forEach(System.out::println);


		// Métodos de consulta

		System.out.println("getById: " + repositorio.getById(idDocumento1));

		String idDocumento2 = repositorio.createEmpty();

		// Añadimos una Url favorita
		Favorito fav3 = new Favorito();
		fav3.setEtiquetas(new HashSet<String>());
		fav3.setUrl("UrlFav3");
		Set<String> et3 = new HashSet<String>();
		et3.add("et7");
		et3.add("et8");
		et3.add("et9");
		fav3.setEtiquetas(et3);

		repositorio.addUrlFavorita(idDocumento2, fav3);

		// Añadimos otra Url favorita
		Favorito fav4 = new Favorito();
		fav4.setUrl("UrlFav2");
		Set<String> et4 = new HashSet<String>();
		et4.add("et4");
		et4.add("et5");
		et4.add("et6");
		fav4.setEtiquetas(et4);
		
		repositorio.addUrlFavorita(idDocumento2, fav4);
		
		//Borrar Url favorita
		
		repositorio.deleteUrlFavorita(idDocumento2, fav4);


		System.out.println("fin.");

	}
}

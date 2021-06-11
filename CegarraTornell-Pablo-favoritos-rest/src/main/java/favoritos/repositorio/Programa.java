package favoritos.repositorio;

import java.util.HashSet;
import java.util.Set;

import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;

public class Programa {

	public static void main(String[] args) throws Exception {

		RepositorioEnlacesFavoritos repositorio = FactoriaRepositorioEnlacesFavoritos.getRepositorio();

		// Creamos un documento vacio
		String idDocumento1 = repositorio.createEmpty();

		// Añadimos una Url favorita
		Favorito fav1 = new Favorito();
		fav1.setEtiquetas(new Etiquetas());
		fav1.setUrl("https://datos.madrid.es/egob/catalogo/tipo/evento/11340884.json");
		Etiquetas et1 = new Etiquetas();
		et1.getEtiquetas().add("et1");
		et1.getEtiquetas().add("et2");
		et1.getEtiquetas().add("et3");
		fav1.setEtiquetas(et1);

		repositorio.addUrlFavorita(idDocumento1, fav1);

		// Añadimos otra Url favorita
		Favorito fav2 = new Favorito();
		fav2.setUrl("UrlFav2");
		Etiquetas et2 =  new Etiquetas();
		et2.getEtiquetas().add("et4");
		et2.getEtiquetas().add("et5");
		et2.getEtiquetas().add("et6");
		fav2.setEtiquetas(et2);
		
		repositorio.addUrlFavorita(idDocumento1, fav2);
		
		System.out.println("getAllEtiquetas: ");
		repositorio.getAllEtiquetas(idDocumento1).getEtiquetas().forEach(System.out::println);


		// Métodos de consulta

		System.out.println("getById: " + repositorio.getById(idDocumento1));

		String idDocumento2 = repositorio.createEmpty();

		// Añadimos una Url favorita
		Favorito fav3 = new Favorito();
		fav3.setEtiquetas(new Etiquetas());
		fav3.setUrl("UrlFav3");
		Etiquetas et3 = new Etiquetas();
		et3.getEtiquetas().add("et7");
		et3.getEtiquetas().add("et8");
		et3.getEtiquetas().add("et9");
		fav3.setEtiquetas(et3);

		repositorio.addUrlFavorita(idDocumento2, fav3);

		// Añadimos otra Url favorita
		Favorito fav4 = new Favorito();
		fav4.setUrl("UrlFav2");
		Etiquetas et4 = new Etiquetas();
		et4.getEtiquetas().add("et4");
		et4.getEtiquetas().add("et5");
		et4.getEtiquetas().add("et6");
		fav4.setEtiquetas(et4);
		
		repositorio.addUrlFavorita(idDocumento2, fav4);
		
		//Borrar Url favorita
		
		repositorio.deleteUrlFavorita(idDocumento2, fav4);


		System.out.println("fin.");

	}
}

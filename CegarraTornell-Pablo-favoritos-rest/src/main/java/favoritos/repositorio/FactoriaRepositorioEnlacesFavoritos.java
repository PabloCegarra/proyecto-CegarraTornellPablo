package favoritos.repositorio;

public class FactoriaRepositorioEnlacesFavoritos {

	private static RepositorioEnlacesFavoritos repository = null;
	
	public static RepositorioEnlacesFavoritos getRepositorio() {
		if (repository == null) {
			repository = new RepositorioEnlacesFavoritosMongoDB();
		}
		return repository;
	}
	
	// TODO: configuracion de la clase con propiedades
	
}

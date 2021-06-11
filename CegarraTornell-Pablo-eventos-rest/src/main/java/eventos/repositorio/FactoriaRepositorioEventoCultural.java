package eventos.repositorio;

public class FactoriaRepositorioEventoCultural {

	private static RepositorioEventoCultural repository = null;
	
	public static RepositorioEventoCultural getRepositorio() {
		if (repository == null) {
			repository = new RepositorioEventoCulturalXML();
		}
		return repository;
	}
}

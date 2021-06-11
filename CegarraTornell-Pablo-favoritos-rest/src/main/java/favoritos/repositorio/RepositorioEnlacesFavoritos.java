package favoritos.repositorio;

import java.util.Set;

import favoritos.dominio.EnlaceFavoritos;
import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;


public interface RepositorioEnlacesFavoritos extends Repositorio<EnlaceFavoritos, String> {
	
	public String createEmpty()  throws RepositorioException;
	
	public void addUrlFavorita(String id, Favorito favorito)
			throws RepositorioException, EntidadNoEncontrada;
	
	public void deleteUrlFavorita(String id, Favorito favorito)
			throws RepositorioException, EntidadNoEncontrada;
	
	public Etiquetas getAllEtiquetas(String id) throws RepositorioException;

	public Set<String> getURLsByEtiqueta(String id, String etiquetaQuery) throws RepositorioException;
	
}

package arso21.mongodb;

import java.util.List;
import java.util.Set;

import arso21.repositorio.EntidadNoEncontrada;
import arso21.repositorio.Repositorio;
import arso21.repositorio.RepositorioException;

public interface RepositorioEnlacesFavoritos extends Repositorio<EnlaceFavoritos, String> {
	
	public String createEmpty()  throws RepositorioException;
	
	public void addUrlFavorita(String id, Favorito favorito)
			throws RepositorioException, EntidadNoEncontrada;
	
	public void deleteUrlFavorita(String id, Favorito favorito)
			throws RepositorioException, EntidadNoEncontrada;
	
	public Set<String> getAllEtiquetas(String id) throws RepositorioException;

	public Set<String> getURLsByEtiqueta(String id, String etiquetaQuery) throws RepositorioException;
	
}

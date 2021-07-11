package favoritos.servicio;

import java.util.Set;

import favoritos.dominio.EnlaceFavoritos;
import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;
import favoritos.repositorio.EntidadNoEncontrada;
import favoritos.repositorio.RepositorioException;

public interface IFavoritosService {
	
	String createEmpty() throws RepositorioException;
	
	void addUrl(String id, Favorito favorito)  throws RepositorioException, EntidadNoEncontrada;
	
	void deleteUrl(String id, Favorito favorito)throws RepositorioException, EntidadNoEncontrada;
	
	Etiquetas getAllEtiquetas(String id) throws RepositorioException, EntidadNoEncontrada;
	
	EnlaceFavoritos getEnlaceById(String id)throws RepositorioException, EntidadNoEncontrada ;
	
	Set<String> getUrlByEtiqueta(String id, String etiquetaQuery) throws RepositorioException, EntidadNoEncontrada;	

}

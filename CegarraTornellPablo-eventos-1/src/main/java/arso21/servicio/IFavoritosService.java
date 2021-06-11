package arso21.servicio;

import java.util.Set;

import arso21.mongodb.EnlaceFavoritos;
import arso21.mongodb.Etiquetas;
import arso21.mongodb.Favorito;
import arso21.repositorio.EntidadNoEncontrada;
import arso21.repositorio.RepositorioException;


public interface IFavoritosService {
	
	String createEmptyDocument() throws RepositorioException;
	
	void addUrl(String id, Favorito favorito)  throws RepositorioException, EntidadNoEncontrada;
	
	void deleteUrl(String id, Favorito favorito)throws RepositorioException, EntidadNoEncontrada;
	
	Etiquetas getAllEtiquetas(String id)throws RepositorioException;
	
	EnlaceFavoritos getEnlaceById(String id)throws RepositorioException, EntidadNoEncontrada ;
	
	Set<String> getUrlByEtiqueta(String id, String etiquetaQuery)throws RepositorioException;

}

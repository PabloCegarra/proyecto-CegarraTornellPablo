package arso21.servicio;

import java.util.Set;

import arso21.mongodb.EnlaceFavoritos;
import arso21.mongodb.Etiquetas;
import arso21.mongodb.FactoriaRepositorioEnlacesFavoritos;
import arso21.mongodb.Favorito;
import arso21.mongodb.RepositorioEnlacesFavoritos;
import arso21.repositorio.EntidadNoEncontrada;
import arso21.repositorio.RepositorioException;


public class FavoritosServiceImpl implements IFavoritosService{
	
	private static FavoritosServiceImpl instancia;
	
	private RepositorioEnlacesFavoritos repositorio = FactoriaRepositorioEnlacesFavoritos.getRepositorio();

	
	public static FavoritosServiceImpl getInstancia(){

		if (instancia == null)
			instancia = new FavoritosServiceImpl();

		return instancia;
	}


	@Override
	public String createEmptyDocument() throws RepositorioException {
		
			return repositorio.createEmpty();
	}

	@Override
	public void addUrl(String id, Favorito favorito) throws RepositorioException, EntidadNoEncontrada {
		repositorio.addUrlFavorita(id, favorito);
	}

	@Override
	public void deleteUrl(String id, Favorito favorito) throws RepositorioException, EntidadNoEncontrada {
		repositorio.deleteUrlFavorita(id, favorito);
	}

	@Override
	public Etiquetas getAllEtiquetas(String id) throws RepositorioException {
		return repositorio.getAllEtiquetas(id);
	}

	@Override
	public EnlaceFavoritos getEnlaceById(String id) throws RepositorioException, EntidadNoEncontrada {
		return repositorio.getById(id);
	}

	@Override
	public Set<String> getUrlByEtiqueta(String id, String etiquetaQuery) throws RepositorioException {
		return repositorio.getURLsByEtiqueta(id, etiquetaQuery);
	}
	
	

}

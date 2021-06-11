package favoritos.servicio;

import java.util.Set;


import favoritos.dominio.EnlaceFavoritos;
import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;
import favoritos.repositorio.EntidadNoEncontrada;
import favoritos.repositorio.FactoriaRepositorioEnlacesFavoritos;
import favoritos.repositorio.RepositorioEnlacesFavoritos;
import favoritos.repositorio.RepositorioException;

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

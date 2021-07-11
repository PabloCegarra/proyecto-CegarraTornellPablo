package favoritos.servicio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Filters;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import favoritos.dominio.EnlaceFavoritos;
import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;
import favoritos.repositorio.EntidadNoEncontrada;
import favoritos.repositorio.FactoriaRepositorioEnlacesFavoritos;
import favoritos.repositorio.RepositorioEnlacesFavoritos;
import favoritos.repositorio.RepositorioException;

public class FavoritosServiceImpl implements IFavoritosService {

	private static FavoritosServiceImpl instancia;

	private RepositorioEnlacesFavoritos repositorio = FactoriaRepositorioEnlacesFavoritos.getRepositorio();

	public static FavoritosServiceImpl getInstancia() {

		if (instancia == null)
			instancia = new FavoritosServiceImpl();

		return instancia;
	}

	@Override
	public String createEmpty() throws RepositorioException {
		try {
			EnlaceFavoritos enlace = new EnlaceFavoritos();
			enlace.setId(new ObjectId());

			enlace.setFavoritos(new HashSet<Favorito>());

			repositorio.add(enlace);

			return enlace.getIdentificador();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	protected String convertToJson(Favorito favoritos) throws JsonProcessingException {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(favoritos);
		return jsonStr;

	}

	protected void enviarFavoritoCola(String jsonFavorito) {
		ConnectionFactory factory = new ConnectionFactory();
		try {
			try {
				factory.setUri("amqps://trphoxnx:RKQMAjh0jlI6vyZuWn3s2fG1Og5o87Nu@squid.rmq.cloudamqp.com/trphoxnx");
			} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Connection connection;
			connection = factory.newConnection();
			Channel channel = connection.createChannel();

			String routingKey = "favoritos-key";
			String exchange = "amq.direct";

			channel.basicPublish(exchange, routingKey,
					new AMQP.BasicProperties.Builder().contentType("application/json").build(),
					jsonFavorito.getBytes());

			channel.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	

	@Override
	public void addUrl(String id, Favorito favorito) throws RepositorioException, EntidadNoEncontrada {

		EnlaceFavoritos enlace = repositorio.getById(id);

		Set<Favorito> favoritos = enlace.getFavoritos();

		String jsonFavorito;
		try {
			jsonFavorito = convertToJson(favorito);
			enviarFavoritoCola(jsonFavorito);

			favoritos.add(favorito);

			enlace.setFavoritos(favoritos);

			repositorio.update(enlace);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}



	}

	@Override
	public void deleteUrl(String id, Favorito favorito) throws RepositorioException, EntidadNoEncontrada {
		EnlaceFavoritos enlace = repositorio.getById(id);
		Set<Favorito> favoritos = enlace.getFavoritos();
		Favorito favoritoEliminar = null;

		for (Favorito fav : favoritos) {
			if (fav.getUrl().equals(favorito.getUrl()))
				favoritoEliminar = fav;
		}
		if (favoritoEliminar != null)
			favoritos.remove(favoritoEliminar);
		enlace.setFavoritos(favoritos);
		repositorio.update(enlace);
	}

	@Override
	public Etiquetas getAllEtiquetas(String id) throws RepositorioException, EntidadNoEncontrada {
		EnlaceFavoritos enlace = repositorio.getById(id);
		Etiquetas allEtiquetas = new Etiquetas();
		if (enlace.getFavoritos() != null) {
			Set<Favorito> favoritos = enlace.getFavoritos();
			for (Favorito favorito : favoritos) {
				Etiquetas etiquetas = favorito.getEtiquetas();
				allEtiquetas.getEtiquetas().addAll(etiquetas.getEtiquetas());
			}
		}
		return allEtiquetas;
	}

	@Override
	public EnlaceFavoritos getEnlaceById(String id) throws RepositorioException, EntidadNoEncontrada {
		return repositorio.getById(id);
	}

	@Override
	public Set<String> getUrlByEtiqueta(String id, String etiquetaQuery) throws RepositorioException, EntidadNoEncontrada {
		EnlaceFavoritos enlace = repositorio.getById(id);
		Set<String> allUrls = new HashSet<>();
		
		if (enlace.getFavoritos() != null) {
			Set<Favorito> favoritos = enlace.getFavoritos();
			for (Favorito favorito : favoritos) {
				if (favorito.getEtiquetas() != null) {
					Etiquetas etiquetas = favorito.getEtiquetas();
					if (etiquetas.getEtiquetas().contains(etiquetaQuery))
						allUrls.add(favorito.getUrl());
				}
			}
		}

		return allUrls;
	}

}

package arso21.mongodb;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import arso21.repositorio.EntidadNoEncontrada;
import arso21.repositorio.RepositorioException;

public class RepositorioEnlacesFavoritosMongoDB implements RepositorioEnlacesFavoritos {

	private MongoCollection<EnlaceFavoritos> enlaces;

	public RepositorioEnlacesFavoritosMongoDB() {

		// TODO: la cadena de conexión debería obtenerse de una propiedad

		String uriString = "mongodb+srv://arso:arso-21@cluster0.wkdnc.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

		ConnectionString connectionString = new ConnectionString(uriString);

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);

		MongoDatabase db = mongoClient.getDatabase("UrlFavoritos");

		this.enlaces = db.getCollection("favoritos", EnlaceFavoritos.class);

	}

	/** Métodos de apoyo **/

	protected boolean checkDocument(ObjectId id) {

		return enlaces.find(Filters.eq("_id", id)).first() != null;
	}

	/**
	 * fin métodos de apoyo
	 * 
	 * @throws RepositorioException
	 **/

	@Override
	public String createEmpty() throws RepositorioException {
		try {
			EnlaceFavoritos enlace = new EnlaceFavoritos();
			enlace.setId(new ObjectId());

			enlace.setFavoritos(new HashSet<Favorito>());

			enlaces.insertOne(enlace);

			return enlace.getIdentificador();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void addUrlFavorita(String id, Favorito favorito) throws RepositorioException, EntidadNoEncontrada {

		EnlaceFavoritos enlace = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();

		if (!checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());

		try {
			Set<Favorito> favoritos = enlace.getFavoritos();

			favoritos.add(favorito);

			enlace.setFavoritos(favoritos);

			enlaces.replaceOne(Filters.eq("_id", enlace.getId()), enlace);

		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public void deleteUrlFavorita(String id, Favorito favorito) throws RepositorioException, EntidadNoEncontrada {

		EnlaceFavoritos enlace = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();

		Favorito favoritoEliminar = null;

		if (!checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());

		try {
			Set<Favorito> favoritos = enlace.getFavoritos();

			for (Favorito fav : favoritos) {
				if (fav.getUrl().equals(favorito.getUrl()))
					favoritoEliminar = fav;
			}

			if (favoritoEliminar != null)
				favoritos.remove(favoritoEliminar);

			enlace.setFavoritos(favoritos);

			update(enlace);

		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public Set<String> getAllEtiquetas(String id) throws RepositorioException {

		EnlaceFavoritos enlace = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();

		Set<String> allEtiquetas = new HashSet<>();

		if (enlace.getFavoritos() != null) {
			Set<Favorito> favoritos = enlace.getFavoritos();
			for (Favorito favorito : favoritos) {
				Set<String> etiquetas = favorito.getEtiquetas();
				allEtiquetas.addAll(etiquetas);
			}
		}

		return allEtiquetas;
	}

	@Override
	public Set<String> getURLsByEtiqueta(String id, String etiquetaQuery) throws RepositorioException {

		EnlaceFavoritos enlace = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();

		Set<String> allUrls = new HashSet<>();


		if (enlace.getFavoritos() != null) {
			Set<Favorito> favoritos = enlace.getFavoritos();
			for (Favorito favorito : favoritos) {
				if (favorito.getEtiquetas() != null) {
					Set<String> etiquetas = favorito.getEtiquetas();
					if (etiquetas.contains(etiquetaQuery))
						allUrls.add(favorito.getUrl());
				}
			}
		}

		return allUrls;
	}

	@Override
	public String add(EnlaceFavoritos enlace) throws RepositorioException {

		try {
			enlace.setId(new ObjectId());

			enlaces.insertOne(enlace);

			return enlace.getIdentificador();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void update(EnlaceFavoritos enlace) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());

		try {

			enlaces.replaceOne(Filters.eq("_id", enlace.getId()), enlace);

		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public void delete(EnlaceFavoritos enlace) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());

		try {
			enlaces.deleteOne(Filters.eq("_id", enlace.getId()));
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public EnlaceFavoritos getById(String id) throws RepositorioException, EntidadNoEncontrada {

		EnlaceFavoritos enlace = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();

		if (enlace == null)
			throw new EntidadNoEncontrada("No existe la entidad con id:" + id);

		return enlace;
	}

	@Override
	public List<EnlaceFavoritos> getAll() throws RepositorioException {

		LinkedList<EnlaceFavoritos> allEnlaces = new LinkedList<>();

		enlaces.find().into(allEnlaces);

		return allEnlaces;
	}

	@Override
	public List<String> getIds() {

		LinkedList<String> allIds = new LinkedList<String>();

		enlaces.find().map(e -> e.getId().toString()).into(allIds);

		return allIds;
	}
}
package favoritos.repositorio;

import java.util.LinkedList;
import java.util.List;

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
import favoritos.dominio.EnlaceFavoritos;


public class RepositorioEnlacesFavoritosMongoDB implements RepositorioEnlacesFavoritos {

	private MongoCollection<EnlaceFavoritos> enlaces;

	public RepositorioEnlacesFavoritosMongoDB() {

		// TODO: la cadena de conexión debería obtenerse de una propiedad

		String uriString = "mongodb://arso:arso-21@cluster0-shard-00-00.wkdnc.mongodb.net:27017,cluster0-shard-00-01.wkdnc.mongodb.net:27017,cluster0-shard-00-02.wkdnc.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-13osw9-shard-0&authSource=admin&retryWrites=true&w=majority";

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
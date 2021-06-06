package arso21.mongodb;

import java.util.Set;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

public class EnlaceFavoritos {

	@BsonId
	private ObjectId id; 
	
    private Set<Favorito> favoritos;

    /** Identificador **/
    
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	@BsonIgnore
	public String getIdentificador() {
		
		return id.toString();
	}
	
	public Set<Favorito> getFavoritos() {
		return favoritos;
	}
	
	public void setFavoritos(Set<Favorito> favoritos) {
		this.favoritos = favoritos;
	}

	@Override
	public String toString() {
		return "Enlace [id=" + id + ", favoritos=" + favoritos +"]";
	}

	
}

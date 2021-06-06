package arso21.mongodb;

import java.util.HashSet;
import java.util.Set;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

public class Favorito {

	private String url;
    private Set<String> etiquetas = new HashSet<String>();

	
    public void setEtiquetas(Set<String> etiquetas) {
		this.etiquetas = etiquetas;
	}
    
    public Set<String> getEtiquetas() {
		return etiquetas;
	}
    
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

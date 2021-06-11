package eventos.servicio;

import java.util.HashSet;
import java.util.Set;


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
		return super.toString();
	}
}

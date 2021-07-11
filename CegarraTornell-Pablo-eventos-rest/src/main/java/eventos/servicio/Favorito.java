package eventos.servicio;

import java.util.HashSet;
import java.util.Set;


public class Favorito {

	private String url;
    private Set<Etiquetas> etiquetas = new HashSet<Etiquetas>();

	
    public void setEtiquetas(Set<Etiquetas> etiquetas) {
		this.etiquetas = etiquetas;
	}
    
    public Set<Etiquetas> getEtiquetas() {
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

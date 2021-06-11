package favoritos.dominio;

import java.util.HashSet;
import java.util.Set;

public class Etiquetas {
	
	Set<String> etiquetas= new HashSet<String>();;
	

	public Set<String> getEtiquetas() {
		return etiquetas;
	}
	
	public void setEtiquetas(Set<String> etiquetas) {
		this.etiquetas = etiquetas;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}

package arso21.mongodb;


public class Favorito {

	private String url;
    private Etiquetas etiquetas = new Etiquetas();

	
    public void setEtiquetas(Etiquetas etiquetas) {
		this.etiquetas = etiquetas;
	}
    
    public Etiquetas getEtiquetas() {
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

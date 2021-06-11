package eventos.sax;

import java.util.Date;

public class EventoResumen {
	
	static final String URL_DATOS = "https://datos.madrid.es/egob/catalogo/tipo/evento/";
	
	private String titulo;
	private String id;
	private String url;
	private Date fechaInicio;
	private String descripcion;
	private String tipo;
	
	
	public EventoResumen() {
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUrl() {
		return URL_DATOS+ getId() + ".xml";
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}

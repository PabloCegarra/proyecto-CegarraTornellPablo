package eventos.sax;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListadoEventos {

	private List<EventoResumen> eventos;
	
	public ListadoEventos() {
	}
	
	
	
	public List<EventoResumen> getEventos() {
		return eventos;
	}
	public void setEventos(List<EventoResumen> eventos) {
		this.eventos = eventos;
	}
}

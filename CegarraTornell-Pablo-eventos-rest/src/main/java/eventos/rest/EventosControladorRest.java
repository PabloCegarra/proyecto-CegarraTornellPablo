package eventos.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.um.eventocultural.EventoCultural;
import eventos.sax.ListadoEventos;
import eventos.servicio.EventosServiceImpl;
import eventos.servicio.IEventosService;
import io.swagger.annotations.Api;

@Api
@Path("eventos")
public class EventosControladorRest {

	IEventosService servicioEventos = EventosServiceImpl.getInstancia();

	@Context
	private UriInfo uriInfo;

	//curl -X GET http://localhost:8080/api/eventos/ListadoEventos
	@GET
	@Path("/ListadoEventos/")
	@Produces(MediaType.APPLICATION_XML)
	public Response getListadoEventos() throws Exception {
		ListadoEventos resultado = servicioEventos.getListadoEventos();

		if (resultado == null)
			throw new IllegalArgumentException("La consulta no de listado de eventos no ha devuelto ningun registro");


		return Response.ok(resultado).build();

	}

	// curl -X GET http://localhost:8080/api/eventos/InfoEvento?url=https://datos.madrid.es/egob/catalogo/tipo/evento/11410328.json
	@GET
	@Path("/InfoEvento")
	@Produces(MediaType.APPLICATION_XML)
	public Response getInfoEvento(@QueryParam("url") String url) throws Exception {

		if (!url.contains("https://datos.madrid.es/egob/catalogo/tipo/evento/"))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("La url especificada no es correcta")
					.build();
		else if (url.contains(".xml"))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("El fichero que se debe pasar debe de ser un json").build();

		EventoCultural resultado = servicioEventos.getInfoEvento(url);

		return Response.ok(resultado).build();

	}

}

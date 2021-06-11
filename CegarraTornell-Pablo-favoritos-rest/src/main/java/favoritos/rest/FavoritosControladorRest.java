package favoritos.rest;

import java.net.URI;
import java.net.URL;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import favoritos.dominio.EnlaceFavoritos;
import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;
import favoritos.dominio.Urls;
import favoritos.repositorio.RepositorioException;
import favoritos.servicio.FavoritosServiceImpl;
import favoritos.servicio.IFavoritosService;
import io.swagger.annotations.Api;

@Api
@Path("favoritos")
public class FavoritosControladorRest {

	
	IFavoritosService servicioFavoritos = FavoritosServiceImpl.getInstancia();
	
	@Context
	private UriInfo uriInfo;
	
	
	//curl -X PUT http://localhost:8080/api/favoritos/CreateDocument/1

	@GET
	@Path("/CreateDocument/")
	public Response createDocument() throws RepositorioException {
		
		String id1 = servicioFavoritos.createEmptyDocument();

		return Response.ok(id1).build();
	}
	
	
	
	//curl -X GET http://localhost:8080/api/favoritos/EnlaceFavoritos/{id}
	//curl -X GET http://localhost:8080/api/favoritos/EnlaceFavoritos/60c22665566b7c052c7eff0c
	@GET
	@Path("/EnlaceFavoritos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentoById(@PathParam("id") String id) throws Exception {


		EnlaceFavoritos resultado = servicioFavoritos.getEnlaceById(id);

		return Response.ok(resultado).build();

	}
	
	//curl -X GET http://localhost:8080/api/favoritos/EnlaceFavoritos/60c3bc61566b7c3378018e55/etiquetas
	@GET
	@Path("/EnlaceFavoritos/{id}/etiquetas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTags(@PathParam("id") String id) throws Exception {


		Etiquetas resultado = servicioFavoritos.getAllEtiquetas(id);
		
		return Response.ok(resultado).build();

	}
	
	//curl -X GET http://localhost:8080/api/favoritos/EnlaceFavoritos/60c3bc61566b7c3378018e55/urls?tag=et1
	@GET
	@Path("/EnlaceFavoritos/{id}/urls")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getURLsByTag(@PathParam("id") String id,@QueryParam("tag") String tag ) throws Exception {

		
		Set<String> urls = servicioFavoritos.getUrlByEtiqueta(id, tag);
		
		Urls resultado = new Urls();
		resultado.setUrls(urls);
		
		ObjectMapper mapperObj = new ObjectMapper();
		String resultadoJSON = mapperObj.writeValueAsString(resultado);
		

		return Response.ok(resultadoJSON).build();

	}
	
//curl -X DELETE http://localhost:8080/api/favoritos/EnlaceFavoritos/60c3bc61566b7c3378018e55/1340884.json
	@DELETE
	@Path("/EnlaceFavoritos/{id}/{url}")
	public Response deleteUrl(@PathParam("id") String id, @PathParam("url") String url) throws Exception {
		Favorito favorito = new Favorito();
		favorito.setUrl(url);
		servicioFavoritos.deleteUrl(id, favorito);
		
		return Response.status(Response.Status.NO_CONTENT).build();
		
	}
	
	
	
	
	
	
	@PUT
	@Path("")
	public Response addUrl(@PathParam("id") String id) throws RepositorioException {
		return Response.created(null).build();
	}

}

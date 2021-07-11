package favoritos.rest;

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


import javax.ws.rs.core.Response;

import favoritos.dominio.EnlaceFavoritos;
import favoritos.dominio.Etiquetas;
import favoritos.dominio.Favorito;
import favoritos.dominio.Urls;
import favoritos.repositorio.EntidadNoEncontrada;
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
	
	
	//curl -X POST http://localhost:8080/api/favoritos/

	@POST
	public Response createDocument() throws RepositorioException {
		
		String id = servicioFavoritos.createEmpty();

		return Response.ok(id).build();
	}
	
	
	
	//curl -X GET http://localhost:8080/api/favoritos/60c3bc61566b7c3378018e55
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentoById(@PathParam("id") String id) throws RepositorioException, EntidadNoEncontrada  {


		EnlaceFavoritos resultado = servicioFavoritos.getEnlaceById(id);

		return Response.ok(resultado).build();

	}
	
	//curl -X GET http://localhost:8080/api/favoritos/60c3bc61566b7c3378018e55/etiquetas
	@GET
	@Path("/{id}/etiquetas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTags(@PathParam("id") String id) throws RepositorioException, EntidadNoEncontrada  {


		Etiquetas resultado = servicioFavoritos.getAllEtiquetas(id);
		
		return Response.ok(resultado).build();

	}
	
	//curl -X GET http://localhost:8080/api/favoritos/60c3bc61566b7c3378018e55/urls?tag=et1
	@GET
	@Path("/{id}/urls")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getURLsByTag(@PathParam("id") String id,@QueryParam("tag") String tag ) throws RepositorioException, EntidadNoEncontrada{

		
		Set<String> urls = servicioFavoritos.getUrlByEtiqueta(id, tag);
		
		Urls resultado = new Urls();
		resultado.setUrls(urls);
		

		return Response.ok(resultado).build();

	}
	
	//curl -X DELETE http://localhost:8080/api/favoritos/60c3bc61566b7c3378018e55/https://datos.madrid.es/egob/catalogo/tipo/evento/11340884.json
	@DELETE
	@Path("/{id}/{url: .*}")
	public Response deleteUrl(@PathParam("id") String id, @PathParam("url") String url) throws RepositorioException, EntidadNoEncontrada {
		Favorito favorito = new Favorito();
		favorito.setUrl(url);
		servicioFavoritos.deleteUrl(id, favorito);
		
		return Response.status(Response.Status.NO_CONTENT).build();
		
	}
	
	
	//curl -X PUT http://localhost:8080/api/favoritos/60c3bc61566b7c3378018e55/https://datos.madrid.es/egob/catalogo/tipo/evento/11340854.json
	@PUT
	@Path("/{id}/{url: .*}")
	public Response addUrl(@PathParam("id") String id, @PathParam("url") String url) throws RepositorioException, EntidadNoEncontrada {
		Favorito favorito = new Favorito();
		favorito.setUrl(url);
		favorito.setEtiquetas(new Etiquetas());
		servicioFavoritos.addUrl(id, favorito);
		return Response.ok(null).build();
	}

}

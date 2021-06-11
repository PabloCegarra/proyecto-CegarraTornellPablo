package eventos.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import eventos.repositorio.EntidadNoEncontrada;

@Provider
public class TratamientoEntidadNoEncontrada implements ExceptionMapper<EntidadNoEncontrada> {
    @Override
    public Response toResponse(EntidadNoEncontrada arg0) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(arg0.getMessage()).build();
    }
}
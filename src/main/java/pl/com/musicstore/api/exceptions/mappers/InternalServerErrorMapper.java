package pl.com.musicstore.api.exceptions.mappers;

import pl.com.musicstore.api.exceptions.Error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(createEntity(exception))
                .build();
    }

    private Error createEntity(Exception exception) {
        return new pl.com.musicstore.api.exceptions.Error(exception.getMessage(), "Wewnętrzny błąd serwera");
    }
}

package pl.com.musicstore.api.exceptions;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserDbException extends WebApplicationException {
    public UserDbException(String message, String userMessage) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(createError(message, userMessage)).build());
    }

    private static Error createError(String message, String userMessage) {
        return new Error(message, userMessage);
    }
}

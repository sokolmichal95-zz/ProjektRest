package pl.com.musicstore.api.exceptions;

public class Error {

    private String message;
    private String userMessage;

    public Error(String message, String userMessage) {
        this.message = message;
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getUserMessage() {
        return userMessage;
    }
}


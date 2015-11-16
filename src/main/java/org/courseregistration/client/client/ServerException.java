package org.courseregistration.client.client;


import org.courseregistration.client.responses.ErrorResponse;

public class ServerException extends Exception {
    private ErrorResponse errorResponse;

    public ServerException(ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
    }

    public ServerException(String message) {
        super(message);
    }
}

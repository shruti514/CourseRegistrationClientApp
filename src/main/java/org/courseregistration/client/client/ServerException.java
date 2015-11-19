package org.courseregistration.client.client;


import org.courseregistration.client.responses.ErrorResponse;

public class ServerException extends Exception {
    private ErrorResponse errorResponse;

    /**
     * retrives the error response in server exception
     * @param errorResponse
     */
    public ServerException(ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
    }

    /**
     * retrives the server exception message
     * @param message
     */
    public ServerException(String message) {
        super(message);
    }
}

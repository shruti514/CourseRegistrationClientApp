package org.courseregistration.client.responses;


import org.courseregistration.client.model.Error;

import java.util.List;

public class ErrorResponse {
    private List<org.courseregistration.client.model.Error> errors;

    /**
     * get errors
     * @return List<Error>
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * set errors
     * @param errors
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}

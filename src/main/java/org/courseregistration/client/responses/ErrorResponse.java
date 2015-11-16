package org.courseregistration.client.responses;


import org.courseregistration.client.model.*;
import org.courseregistration.client.model.Error;

import java.util.List;

public class ErrorResponse {
    private List<org.courseregistration.client.model.Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}

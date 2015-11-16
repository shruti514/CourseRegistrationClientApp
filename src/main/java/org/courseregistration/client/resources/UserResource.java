package org.courseregistration.client.resources;

import org.courseregistration.client.model.LoginRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


public interface UserResource {

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("login")
    Response login(LoginRequest loginRequest);
}

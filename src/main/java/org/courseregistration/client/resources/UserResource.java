package org.courseregistration.client.resources;

import org.courseregistration.client.model.LoginRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


public interface UserResource {

    /**
     * Login
     * @param loginRequest
     * @return Response
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("login")
    Response login(LoginRequest loginRequest);
}

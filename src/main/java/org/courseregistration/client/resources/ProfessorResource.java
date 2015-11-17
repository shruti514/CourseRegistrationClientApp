package org.courseregistration.client.resources;

/**
 * Created by SHITAL on 11/13/2015.
 **/

import org.courseregistration.client.responses.ProfessorResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface ProfessorResource {
    //  get a single professor
    @GET
    @Produces("application/json")
    @Path("/professors/{id}")
    Response getProfessor(@PathParam("id") int id);

    //  get all professors
    @GET
    @Produces("application/json")
    @Path("/professors")
    ProfessorResponse getAllProfessors();

    //  create single professor
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/professors/{id}")
    ProfessorResponse setProfessor(@PathParam("id") int id);

    //  create multiple professors
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/professors")
    ProfessorResponse setMultipleProfessors();

    //  delete a single student
    @DELETE
    @Produces("application/json")
    @Path("professor/{id}")
    ProfessorResponse deleteProfessor(@PathParam("id") int id);

    //  delete multiple professors
    @DELETE
    @Produces("application/json")
    @Path("professor/{ids}")
    ProfessorResponse deleteMultipleProfessors();

    //  update professors details
    @PUT
    @Produces("text/plain")
    @Consumes("application/json")
    @Path("professor/{id}")
    ProfessorResponse updateProfessor(@PathParam("id") int id);

}

